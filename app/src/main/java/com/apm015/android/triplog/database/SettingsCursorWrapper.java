package com.apm015.android.triplog.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.apm015.android.triplog.Settings;;
import com.apm015.android.triplog.database.SettingsDBSchema.SettingsTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam on 24/10/2016.
 */
public class SettingsCursorWrapper extends CursorWrapper{

    public SettingsCursorWrapper(Cursor c) {
        super(c);
    }

    public Settings getSettings() {
        //String uuidString = getString(getColumnIndex(SettingsTable.Cols.UUID));
        String name = getString(getColumnIndex(SettingsTable.Cols.NAME));
        String id = getString(getColumnIndex(SettingsTable.Cols.ID));
        String email = getString(getColumnIndex(SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(SettingsTable.Cols.COMMENT));

        Settings s = new Settings();
        s.setName(name);
        s.setId(id);
        s.setEmail(email);
        s.setGender(gender);
        s.setComment(comment);

        return s;
    }

}
