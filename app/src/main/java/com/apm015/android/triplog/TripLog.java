package com.apm015.android.triplog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.apm015.android.triplog.database.TripCursorWrapper;
import com.apm015.android.triplog.database.TripDBHelper;
import com.apm015.android.triplog.database.TripDBSchema;
import com.apm015.android.triplog.database.TripDBSchema.TripTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 9/10/2016.
 */
public class TripLog {

    private static TripLog sTripLog;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TripLog get(Context context) {

        if(sTripLog == null) {
            sTripLog = new TripLog(context);
        }

        return sTripLog;
    }

    private TripLog(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TripDBHelper(mContext).getWritableDatabase();
    }

    public void addTrip(Trip trip) {
        ContentValues values = getContentValues(trip);

        mDatabase.insert(TripTable.NAME, null, values);
    }

    public void updateTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        ContentValues values = getContentValues(trip);

        mDatabase.update(TripTable.NAME, values, TripTable.Cols.ID + " = ?", new String[] {uuidString});
    }

    public void deleteTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        mDatabase.delete(TripTable.NAME, TripTable.Cols.ID + " = ?", new String[] {uuidString});
    }

    public List<Trip> getTrips() {
        List<Trip> trips = new ArrayList<>();
        TripCursorWrapper c = queryTrips(null, null);

        try {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                trips.add(c.getTrip());
                c.moveToNext();
            }
        } finally {
            c.close();
        }

        return trips;
    }

    public Trip getTrip(UUID id) {
        TripCursorWrapper c = queryTrips(
                TripTable.Cols.ID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (c.getCount() == 0) {
                return null;
            }

            c.moveToFirst();
            return c.getTrip();
        } finally {
            c.close();
        }
    }

    private static ContentValues getContentValues(Trip trip) {
        ContentValues values = new ContentValues();

        values.put(TripTable.Cols.ID, trip.getId().toString());
        values.put(TripTable.Cols.TITLE, trip.getTitle());
        values.put(TripTable.Cols.DATE, trip.getDate().getTime());
        values.put(TripTable.Cols.TYPE, trip.getType());
        values.put(TripTable.Cols.DESTINATION, trip.getDestination());
        values.put(TripTable.Cols.DURATION, trip.getDuration());
        values.put(TripTable.Cols.COMMENT, trip.getComment());

        return values;
    }

    private TripCursorWrapper queryTrips(String whereClause, String[] whereArgs) {
        Cursor c = mDatabase.query(TripTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new TripCursorWrapper(c);
    }

    public File getPhotoFile(Trip trip) {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if(externalFilesDir == null) {
            return null;
        }

        return new File(externalFilesDir, trip.getPhotoFilename());
    }
}
