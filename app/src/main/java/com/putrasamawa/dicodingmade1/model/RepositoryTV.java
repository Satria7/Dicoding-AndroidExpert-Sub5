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

public class RepositoryTV {
    private ArrayList<ItemTV> tv = new ArrayList<>();
    private MutableLiveData<List<ItemTV>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public RepositoryTV(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<ItemTV>> getMutableLiveData() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<WrapperTV> call = apiService.getPopularTV("cf72efb6e9eb91453d9aabf8a9d16ae8","en-US");

        call.enqueue(new Callback<WrapperTV>() {
            @Override
            public void onResponse(Call<WrapperTV> call, Response<WrapperTV> response) {
                WrapperTV mWrapperTV = response.body();
                if (mWrapperTV != null && mWrapperTV.getResults()!= null) {
                    tv = (ArrayList<ItemTV>) mWrapperTV.getResults();
                    mutableLiveData.setValue(tv);
                }
            }

            @Override
            public void onFailure(Call<WrapperTV> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}

/* Copyright Satria Junanda */