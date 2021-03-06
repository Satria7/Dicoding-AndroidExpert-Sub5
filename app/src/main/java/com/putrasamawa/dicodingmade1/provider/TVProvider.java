package com.putrasamawa.dicodingmade1.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.putrasamawa.dicodingmade1.db.TVContract;
import com.putrasamawa.dicodingmade1.db.TVHelper;

import java.util.Objects;

import static com.putrasamawa.dicodingmade1.db.TVContract.AUTHORITY;
import static com.putrasamawa.dicodingmade1.db.TVContract.CONTENT_URI;

/* Copyright Satria Junanda */

public class TVProvider extends ContentProvider{

    private static final int MOVIE = 100;
    private static final int MOVIE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {


        sUriMatcher.addURI(AUTHORITY,
                TVContract.MovieColumns.TABLE_MOVIE, MOVIE);


        sUriMatcher.addURI(AUTHORITY,
                TVContract.MovieColumns.TABLE_MOVIE+ "/#",
                MOVIE_ID);
    }

    private TVHelper movieHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new TVHelper(getContext());
        movieHelper.open();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        Log.v("MovieDetail", ""+match);
        Log.v("MovieDetail", ""+uri);
        Log.v("MovieDetail", ""+uri.getLastPathSegment());
        switch(match){
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added ;

        switch (sUriMatcher.match(uri)){
            case MOVIE:
                added = movieHelper.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int movieDeleted;

        Log.v("MovieDetail1", ""+uri);
        int match = sUriMatcher.match(uri);
        Log.v("MovieDetail1", ""+match);
        switch (match) {
            case MOVIE_ID:
                movieDeleted =  movieHelper.deleteProvider(uri.getLastPathSegment());
                Log.v("MovieDetail1", ""+movieDeleted);
                break;
            default:
                movieDeleted = 0;
                break;
        }

        if (movieDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return movieDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int movieUpdated ;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                movieUpdated =  movieHelper.updateProvider(uri.getLastPathSegment(),values);
                break;
            default:
                movieUpdated = 0;
                break;
        }

        if (movieUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return movieUpdated;
    }
}

/* Copyright Satria Junanda */