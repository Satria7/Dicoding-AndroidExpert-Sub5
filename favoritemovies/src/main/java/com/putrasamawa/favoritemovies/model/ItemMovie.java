package com.putrasamawa.favoritemovies.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/* Copyright Satria Junanda */

public class ItemMovie implements Parcelable {

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

    protected ItemMovie(Parcel in) {
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
        poster_path = in.readString();
        id = in.readString();
    }

    public static final Creator<ItemMovie> CREATOR = new Creator<ItemMovie>() {
        @Override
        public ItemMovie createFromParcel(Parcel in) {
            return new ItemMovie(in);
        }

        @Override
        public ItemMovie[] newArray(int size) {
            return new ItemMovie[size];
        }
    };

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

    public ItemMovie(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(vote_average);
        dest.writeString(poster_path);
        dest.writeString(id);
    }
}


/* Copyright Satria Junanda */