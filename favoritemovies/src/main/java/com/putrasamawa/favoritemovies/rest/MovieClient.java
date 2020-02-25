package com.putrasamawa.favoritemovies.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.putrasamawa.favoritemovies.rest.UtilsConstant.BASE_URL;

/* Copyright Satria Junanda */

public class MovieClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

/* Copyright Satria Junanda */