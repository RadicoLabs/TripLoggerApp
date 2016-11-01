package com.apm015.android.triplog.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.apm015.android.triplog.Trip;
import com.apm015.android.triplog.database.TripDBSchema.TripTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam on 24/10/2016.
 */
public class TripCursorWrapper extends CursorWrapper{

    public TripCursorWrapper(Cursor c) {
        super(c);
    }

    public Trip getTrip() {
        String uuidString = getString(getColumnIndex(TripTable.Cols.ID));
        String title = getString(getColumnIndex(TripTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TripTable.Cols.DATE));
        String type = getString(getColumnIndex(TripTable.Cols.TYPE));
        String destination = getString(getColumnIndex(TripTable.Cols.DESTINATION));
        String duration = getString(getColumnIndex(TripTable.Cols.DURATION));
        String comment= getString(getColumnIndex(TripTable.Cols.COMMENT));

        Trip t = new Trip(UUID.fromString(uuidString));
        t.setTitle(title);
        t.setDate(new Date(date));
        t.setType(type);
        t.setDestination(destination);
        t.setDuration(duration);
        t.setComment(comment);

        return t;
    }
}
