package com.apm015.android.triplog.database;

/**
 * Created by Adam on 24/10/2016.
 */
public class SettingsDBSchema {

    public static final class SettingsTable {
        public static final String NAME = "settings";

        public static final class Cols {
            //public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String ID = "id";
            public static final String EMAIL = "email";
            public static final String GENDER = "gender";
            public static final String COMMENT = "comment";

        }
    }

}
