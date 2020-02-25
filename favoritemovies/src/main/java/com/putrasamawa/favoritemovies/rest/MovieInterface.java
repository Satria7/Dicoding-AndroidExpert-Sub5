package com.putrasamawa.favoritemovies.rest;

import com.putrasamawa.favoritemovies.model.ItemMovie;
import com.putrasamawa.favoritemovies.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* Copyright Satria Junanda */

public interface MovieInterface {

    @GET("movie/{id}")
    Call<ItemMovie> getMovieById(@Path("id") String id, @Query("api_key") String apiKey);

}

/* Copyright Satria Junanda */