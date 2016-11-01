package com.apm015.android.triplog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apm015.android.triplog.database.SettingsCursorWrapper;
import com.apm015.android.triplog.database.SettingsDBHelper;
import com.apm015.android.triplog.database.SettingsDBSchema.SettingsTable;

import java.util.UUID;


/**
 * Created by Adam on 25/10/2016.
 */
public class SettingsLog {

    private static SettingsLog sSettingsLog;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static SettingsLog get(Context context) {
        if(sSettingsLog == null) {
            sSettingsLog = new SettingsLog(context);
        }

        return sSettingsLog;
    }

    private SettingsLog(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SettingsDBHelper(mContext).getWritableDatabase();
    }

    public void updateSettings(Settings settings) {
        String idString = settings.getUUID().toString();
        ContentValues values = getContentValues(settings);

        mDatabase.update(SettingsTable.NAME, values, null, null);
        //mDatabase.update(SettingsTable.NAME, values, SettingsTable.Cols.ID + " = ?", new String[] {"1"});
    }

    private static ContentValues getContentValues(Settings settings) {
        ContentValues values = new ContentValues();
        values.put(SettingsTable.Cols.NAME, settings.getName());
        values.put(SettingsTable.Cols.ID, settings.getId());
        values.put(SettingsTable.Cols.EMAIL, settings.getEmail());
        values.put(SettingsTable.Cols.COMMENT, settings.getComment());

        return values;
    }

    private SettingsCursorWrapper querySettings() {
        Cursor c = mDatabase.query(SettingsTable.NAME,
                null, // Columns - null selects all columns
                null, //whereClause,
                null, //whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new SettingsCursorWrapper(c);
    }

    public Settings getSetting() {
        SettingsCursorWrapper c = querySettings();

        try {
            if (c.getCount() == 0) {
                return null;
            }

            c.moveToFirst();
            return c.getSettings();
        } finally {
            c.close();
        }
    }


}
