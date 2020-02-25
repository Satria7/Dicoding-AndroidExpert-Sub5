package com.putrasamawa.favoritemovies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.putrasamawa.favoritemovies.adapter.MovieAdapter;
import com.putrasamawa.favoritemovies.database.DatabaseContract;
import com.putrasamawa.favoritemovies.model.Item;
import com.putrasamawa.favoritemovies.model.ItemMovie;
import com.putrasamawa.favoritemovies.model.MovieFavorite;
import com.putrasamawa.favoritemovies.rest.MovieClient;
import com.putrasamawa.favoritemovies.rest.MovieInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* Copyright Satria Junanda */


import static com.putrasamawa.favoritemovies.database.DatabaseContract.CONTENT_URI;

/* Copyright Satria Junanda */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.list_fav_mov)
    RecyclerView recyclerView;
    @BindView(R.id.myprogress)
    TextView msg;
    Call<ItemMovie> movieResultCall;
    MovieAdapter adapter;
    ArrayList<ItemMovie> movieResults;
    ArrayList<Item> movieFavorites;
    MovieInterface movieService;

    private final int MOVIE_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        adapter = new MovieAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getSupportLoaderManager().initLoader(MOVIE_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        movieFavorites = new ArrayList<>();
        movieResults = new ArrayList<>();
        return new CursorLoader(this, CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        movieFavorites = getItem(data);
        ArrayList<Item> movieFavoriteArrayList = new ArrayList<>();
        for (int i = 0; i < movieFavoriteArrayList.size(); i++) {
            if (((Item) movieFavoriteArrayList.get(i)).getJenis().equals("movie")) {
                getFavoriteMovies(((Item) movieFavoriteArrayList.get(i)).getText3());
                Log.v("Matt1", "hm:" + movieFavoriteArrayList.get(i).getJenis());
            }
        }
        adapter.notifyDataSetChanged();
        Log.v("Matt1", "List" + movieFavoriteArrayList.size());
        getSupportLoaderManager().destroyLoader(MOVIE_ID);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        movieFavorites = getItem(null);
    }

    private ArrayList<Item> getItem(Cursor cursor) {
        ArrayList<Item> movieFavoriteArrayList = new ArrayList<>();
        try {
            cursor.moveToFirst();
            Item favorite;
            Log.v("Matt1", "Listc" + cursor.getCount());
            if (cursor.getCount() > 0) {
                do {
                    favorite = new Item(cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.MovieColumns.MOVIE_ID)), cursor.getString(cursor.getColumnIndexOrThrow(
                            DatabaseContract.MovieColumns.MOVIE_JENIS)));
                    movieFavoriteArrayList.add(favorite);
                    cursor.moveToNext();
                } while (!cursor.isAfterLast());
            }
            for (int i = 0; i < movieFavoriteArrayList.size(); i++) {
                if (((Item) movieFavoriteArrayList.get(i)).getJenis().equals("movie")) {
                    getFavoriteMovies(((Item) movieFavoriteArrayList.get(i)).getText3());
                    Log.v("Matt1", "hm:" + movieFavoriteArrayList.get(i).getJenis());
                }
            }
        } catch (Exception e) {
//            Toast.makeText(MainActivity.this, R.string.infobug, Toast.LENGTH_SHORT).show();
            msg.setText(getResources().getString(R.string.infobug));
        }

        return movieFavoriteArrayList;
    }

    private void getFavoriteMovies(String id) {
        movieService = MovieClient.getClient().create(MovieInterface.class);
        movieResultCall = movieService.getMovieById(id, "cf72efb6e9eb91453d9aabf8a9d16ae8");

        movieResultCall.enqueue(new Callback<ItemMovie>() {
            @Override
            public void onResponse(Call<ItemMovie> call, Response<ItemMovie> response) {
                movieResults.add(response.body());
                adapter.setMovieResult(movieResults);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (movieFavorites.size() == 0) {
                    msg.setVisibility(View.VISIBLE);
                } else {
                    msg.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemMovie> call, Throwable t) {
                movieResults = null;
                msg.setText(R.string.fail_load);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.removeAllViewsInLayout();
        recyclerView.setAdapter(null);
        recyclerView.smoothScrollToPosition(0);
        if (movieResults != null) {
            movieResults.clear();
            Log.v("soek", "List" + movieResults.size());
            adapter.setMovieResult(movieResults);
            recyclerView.setAdapter(adapter);
        }
        getSupportLoaderManager().restartLoader(MOVIE_ID, null, this);
    }
}
/* Copyright Satria Junanda */