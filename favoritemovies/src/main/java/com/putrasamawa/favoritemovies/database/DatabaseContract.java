package com.putrasamawa.favoritemovies.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/* Copyright Satria Junanda */

import static com.putrasamawa.favoritemovies.database.DatabaseContract.MovieColumns.TABLE_MOVIE;

/* Copyright Satria Junanda */

public class DatabaseContract {
    public static final class MovieColumns implements BaseColumns {

        public static final String TABLE_MOVIE = "movie";
        public static String MOVIE_ID = "movie_id";
        public static String MOVIE_TITLE = "title";
        public static String MOVIE_JENIS = "jenis";
    }

    public static final String AUTHORITY = "com.putrasamawa.dicodingmade1";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}

/* Copyright Satria Junanda */
