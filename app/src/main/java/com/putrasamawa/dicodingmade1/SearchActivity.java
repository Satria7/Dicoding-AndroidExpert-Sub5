package com.putrasamawa.dicodingmade1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.putrasamawa.dicodingmade1.adapter.AdapterMovie;
import com.putrasamawa.dicodingmade1.adapter.AdapterTV;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemMovie;
import com.putrasamawa.dicodingmade1.model.ItemTV;
import com.putrasamawa.dicodingmade1.model.WrapperMovie;
import com.putrasamawa.dicodingmade1.model.WrapperTV;
import com.putrasamawa.dicodingmade1.service.RestApiService;
import com.putrasamawa.dicodingmade1.service.RetrofitInstance;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* Copyright Satria Junanda */

public class SearchActivity extends AppCompatActivity {

    AdapterMovie movieAdapter;
    AdapterTV movieAdapter2;

    RecyclerView recyclerView;
    TextView progressBar;

    List<ItemMovie> movieList;
    RestApiService movieService;
    Call<WrapperMovie> movieCall;

    List<ItemTV> movieList2;
    Call<WrapperTV> movieCall2;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (TextView) findViewById(R.id.myprogress);
        searchView = (SearchView) findViewById(R.id.mSearch);

        String type = getIntent().getStringExtra("key");

        if (type.equals("movie")){
            initView();
        }else if(type.equals("tv")){
            initView2();
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                if(type.equals("movie")) {
                    getMovies(q);
                }else if(type.equals("tv")){
                    getMovies2(q);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String q) {
                if(type.equals("movie")) {
                    getMovies(q);
                }else if(type.equals("tv")){
                    getMovies2(q);
                }


                return false;
            }
        });

    }

    void initView() {

        movieAdapter = new AdapterMovie(this);
        recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    void initView2() {

        movieAdapter2 = new AdapterTV(this);
        recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getMovies(final String q) {
        movieService = RetrofitInstance.getApiService();
        movieCall = movieService.getMovieBySearch(q, "cf72efb6e9eb91453d9aabf8a9d16ae8");

        movieCall.enqueue(new Callback<WrapperMovie>() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onResponse(@NonNull Call<WrapperMovie> call, @NonNull Response<WrapperMovie> response) {
                if (response.body() != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        movieList = Objects.requireNonNull(response.body()).getResults();
                        recyclerView.setHasFixedSize(true);
                        progressBar.setVisibility(View.GONE);
                    }
                }
                movieAdapter.setmMovieList(movieList);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<WrapperMovie> call, @NonNull Throwable t) {
                Toast.makeText(SearchActivity.this, R.string.fail_load
                        , Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void getMovies2(final String q) {
        movieService = RetrofitInstance.getApiService();
        movieCall2 = movieService.getTVBySearch(q, "cf72efb6e9eb91453d9aabf8a9d16ae8");

        movieCall2.enqueue(new Callback<WrapperTV>() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onResponse(@NonNull Call<WrapperTV> call, @NonNull Response<WrapperTV> response) {
                if (response.body() != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        movieList2 = Objects.requireNonNull(response.body()).getResults();
                        recyclerView.setHasFixedSize(true);
                        progressBar.setVisibility(View.GONE);
                    }
                }
                movieAdapter2.setmTVList(movieList2);
                recyclerView.setAdapter(movieAdapter2);
            }

            @Override
            public void onFailure(@NonNull Call<WrapperTV> call, @NonNull Throwable t) {
                Toast.makeText(SearchActivity.this, R.string.fail_load
                        , Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void exit(View view) {
        finish();
    }
}

/* Copyright Satria Junanda */