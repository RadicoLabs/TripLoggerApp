package com.apm015.android.triplog.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apm015.android.triplog.TripLog;
import com.apm015.android.triplog.database.TripDBSchema.TripTable;

/**
 * Created by Adam on 24/10/2016.
 */
public class TripDBHelper extends SQLiteOpenHelper {

    private TripLog tl;

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tripDB.db";

    public TripDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TripTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TripTable.Cols.ID + ", " +
                TripTable.Cols.TITLE + ", " +
                TripTable.Cols.DATE + ", " +
                TripTable.Cols.TYPE + ", " +
                TripTable.Cols.DESTINATION + ", " +
                TripTable.Cols.DURATION + ", " +
                TripTable.Cols.COMMENT +
                ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
