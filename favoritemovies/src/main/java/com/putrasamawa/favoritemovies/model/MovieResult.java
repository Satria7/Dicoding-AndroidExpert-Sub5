package com.putrasamawa.favoritemovies.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/* Copyright Satria Junanda */

@SuppressWarnings("unused")
public class MovieResult implements Parcelable {

    @SerializedName("id")
    private String mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("poster_path")
    private String mPosterPath;


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }


    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }


    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPosterPath);

    }

    public MovieResult() {
    }

    protected MovieResult(Parcel in) {
        this.mId = in.readString();
        this.mOriginalLanguage = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPosterPath = in.readString();
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel source) {
            return new MovieResult(source);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    @Override
    public String toString() {
        return getPosterPath() + getId();
    }
}

/* Copyright Satria Junanda */