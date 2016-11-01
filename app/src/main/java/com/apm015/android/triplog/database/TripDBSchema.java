package com.apm015.android.triplog.database;

/**
 * Created by Adam on 24/10/2016.
 */
public class TripDBSchema {

    public static final class TripTable {
        public static final String NAME = "trips";

        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TYPE = "type";
            public static final String DESTINATION = "destination";
            public static final String DURATION = "duration";
            public static final String COMMENT = "comment";
            public static final String PHOTO = "photo";
        }

    }

}
