package com.putrasamawa.dicodingmade1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.putrasamawa.dicodingmade1.model.ItemMovie;
import com.putrasamawa.dicodingmade1.model.RepositoryMovie;

import java.util.List;

/* Copyright Satria Junanda */

public class ViewModelMovie extends AndroidViewModel {
    private RepositoryMovie repositoryMovie;

    public ViewModelMovie(@NonNull Application application) {
        super(application);
        repositoryMovie = new RepositoryMovie(application);
    }

    public LiveData<List<ItemMovie>> getAllBlog() {
        return repositoryMovie.getMutableLiveData();
    }


}

/* Copyright Satria Junanda */