package com.putrasamawa.dicodingmade1.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* Copyright Satria Junanda */

public class RetrofitInstance {
private static final String BASE_URL= "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static RestApiService getApiService() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(RestApiService.class);

    }

}

/* Copyright Satria Junanda */