package com.putrasamawa.dicodingmade1.service;

import com.putrasamawa.dicodingmade1.model.ItemMovie;
import com.putrasamawa.dicodingmade1.model.ItemTV;
import com.putrasamawa.dicodingmade1.model.WrapperMovie;
import com.putrasamawa.dicodingmade1.model.WrapperTV;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* Copyright Satria Junanda */

public interface RestApiService {

    @GET("discover/movie")
    Call<WrapperMovie> getPopularMovie(@Query("api_key") String api, @Query("language") String language);


    @GET("discover/tv")
    Call<WrapperTV> getPopularTV(@Query("api_key") String api, @Query("language") String language);

    @GET("movie/{id}")
    Call<ItemMovie> getMovieById(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<ItemTV> getTVById(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<WrapperMovie> getMovieBySearch(@Query("query") String q, @Query("api_key") String apiKey);


    @GET("search/tv")
    Call<WrapperTV> getTVBySearch(@Query("query") String q, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<WrapperMovie> getUpcomingMovie(@Query("api_key") String key, @Query("primary_release_date.gte") String paramString1, @Query("primary_release_date.lte") String paramString2);
}



/* Copyright Satria Junanda */