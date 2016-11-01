package com.apm015.android.triplog.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apm015.android.triplog.Settings;
import com.apm015.android.triplog.database.SettingsDBSchema.SettingsTable;


/**
 * Created by Adam on 24/10/2016.
 */
public class SettingsDBHelper extends SQLiteOpenHelper {

    private Settings mSettings;

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "settingsDB.db";

    public SettingsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + SettingsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                SettingsTable.Cols.NAME + ", " +
                SettingsTable.Cols.ID + ", " +
                SettingsTable.Cols.EMAIL + ", " +
                SettingsTable.Cols.GENDER + ", " +
                SettingsTable.Cols.COMMENT +
                ")" );

        insertDefaultEntries(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDefaultEntries(SQLiteDatabase db) {
        mSettings = new Settings();

        mSettings.setName("Adam Martin");
        mSettings.setId("apm015");
        mSettings.setEmail("apm015@student.usd.edu.au");
        mSettings.setGender("Male");
        mSettings.setComment("testcomment");

        ContentValues values = getContentValues(mSettings);
        db.insert(SettingsTable.NAME, null, values);
    }
    
    private static ContentValues getContentValues(Settings mSettings) {
        ContentValues values = new ContentValues();

        values.put(SettingsTable.Cols.NAME, mSettings.getName());
        values.put(SettingsTable.Cols.ID, mSettings.getId());
        values.put(SettingsTable.Cols.EMAIL, mSettings.getEmail());
        values.put(SettingsTable.Cols.GENDER, mSettings.getGender());
        values.put(SettingsTable.Cols.COMMENT, mSettings.getComment());

        return values;
    }
}
