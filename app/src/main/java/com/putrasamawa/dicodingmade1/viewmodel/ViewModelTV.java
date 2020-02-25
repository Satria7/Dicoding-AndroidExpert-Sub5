package com.putrasamawa.dicodingmade1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.putrasamawa.dicodingmade1.model.ItemTV;
import com.putrasamawa.dicodingmade1.model.RepositoryTV;

import java.util.List;

/* Copyright Satria Junanda */

public class ViewModelTV extends AndroidViewModel {
    private RepositoryTV repositoryTV;

    public ViewModelTV(@NonNull Application application) {
        super(application);
        repositoryTV = new RepositoryTV(application);
    }

    public LiveData<List<ItemTV>> getAll() {
        return repositoryTV.getMutableLiveData();
    }


}

/* Copyright Satria Junanda */
