package com.putrasamawa.dicodingmade1.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/* Copyright Satria Junanda */

public class Item implements Parcelable {
    private String mImageResource;
    private String mText1;
    private String mText2;
    private String mText3;
    private String mJenis;


    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Item(String imageResource, String text1, String text2,String text3,String jenis) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
        mJenis = jenis;

    }

    protected Item(Parcel in) {
        mImageResource = in.readString();
        mText1 = in.readString();
        mText2 = in.readString();
        mText3 = in.readString();
        mJenis = in.readString();

    }

    public Item(String id) {
        this.mText3 = id;
    }
    public String getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
    public String getText3() {
        return mText3;
    }
    public String getJenis() {
        return mJenis;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageResource);
        dest.writeString(mText1);
        dest.writeString(mText2);
        dest.writeString(mText3);
        dest.writeString(mJenis);

    }

    public Item(String id, String jenis2) {
        this.mText3 = id;
        this.mJenis = jenis2;
    }
}

/* Copyright Satria Junanda */