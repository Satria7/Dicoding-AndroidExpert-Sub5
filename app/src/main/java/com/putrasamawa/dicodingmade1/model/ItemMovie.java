package com.putrasamawa.dicodingmade1.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.putrasamawa.dicodingmade1.db.MovieContract;

import static com.putrasamawa.dicodingmade1.db.MovieContract.MovieColumns.MOVIE_ID;
import static com.putrasamawa.dicodingmade1.db.MovieContract.MovieColumns.MOVIE_IMAGE;
import static com.putrasamawa.dicodingmade1.db.MovieContract.MovieColumns.MOVIE_TITLE;

/* Copyright Satria Junanda */

public class ItemMovie {

    @SerializedName("original_title")
    private String original_title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("vote_average")
    private String vote_average;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ItemMovie(Cursor cursor) {
        this.id = MovieContract.getColumnString(cursor,MOVIE_ID);
        this.original_title = MovieContract.getColumnString(cursor, MOVIE_TITLE);
        this.poster_path = MovieContract.getColumnString(cursor, MOVIE_IMAGE);
    }
    public ItemMovie(){

    }
}

/* Copyright Satria Junanda */