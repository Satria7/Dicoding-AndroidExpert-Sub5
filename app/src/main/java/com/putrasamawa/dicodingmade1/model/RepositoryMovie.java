package com.putrasamawa.dicodingmade1.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;


import com.putrasamawa.dicodingmade1.service.RestApiService;
import com.putrasamawa.dicodingmade1.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* Copyright Satria Junanda */

public class RepositoryMovie {
    private ArrayList<ItemMovie> movies = new ArrayList<>();
    private MutableLiveData<List<ItemMovie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public RepositoryMovie(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<ItemMovie>> getMutableLiveData() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<WrapperMovie> call = apiService.getPopularMovie("cf72efb6e9eb91453d9aabf8a9d16ae8", "en-US");

        call.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                WrapperMovie mWrapperMovie = response.body();
                if (mWrapperMovie != null && mWrapperMovie.getResults() != null) {
                    movies = (ArrayList<ItemMovie>) mWrapperMovie.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}

/* Copyright Satria Junanda */
