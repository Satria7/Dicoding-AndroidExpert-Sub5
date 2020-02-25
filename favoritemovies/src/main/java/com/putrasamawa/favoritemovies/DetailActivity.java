package com.putrasamawa.favoritemovies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Printer;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.putrasamawa.favoritemovies.database.DatabaseContract;
import com.putrasamawa.favoritemovies.model.Item;
import com.putrasamawa.favoritemovies.model.ItemMovie;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/* Copyright Satria Junanda */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.text2_activity2)
    TextView tvOverview;
    @BindView(R.id.text1_activity2)
    TextView tvTitle;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.image_activity2)
    ImageView poster;
    @BindView(R.id.btn_fav)
    ImageView fav;
    String id,des,imgs,title;
    ItemMovie movie;

    boolean isfavorite;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
        Intent intent = getIntent();
        Item exampleModel = intent.getParcelableExtra("Model");

        title = exampleModel.getImageResource();
        des = exampleModel.getText1();
        imgs = exampleModel.getText2();
        id = exampleModel.getText3();
        updateImage();

        if (isFavorite(id)) {
            isfavorite = true;
            fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit2));
        } else {
            fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit1));
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isfavorite) {
                    isfavorite = true;
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit2));
                    Snackbar.make(v, R.string.add_fav, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {
                    isfavorite = false;
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit1));
                    Snackbar.make(v, R.string.remove_fav, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                saveInFavorite();
            }
        });
    }

    private void saveInFavorite() {
        if (isfavorite) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.MovieColumns.MOVIE_ID, id);
            contentValues.put(DatabaseContract.MovieColumns.MOVIE_TITLE, title);
            getContentResolver().insert(DatabaseContract.CONTENT_URI, contentValues);
        }else{
            Uri uri = DatabaseContract.CONTENT_URI;
            uri = uri.buildUpon().appendPath(id).build();
            getContentResolver().delete(uri, null, null);
        }
    }


    void updateImage() {
        try {

            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w342/" + imgs)
                    .into(poster);
        }catch (Exception e){

        }
        tvTitle.setText(title);
        tvOverview.setText(des);
        progressBar.setVisibility(View.GONE);

    }
    public void exit(View view) {
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean isFavorite(String id) {
        String selection = " movie_id = ?";
        String[] selectionArgs = {id};
        String[] projection = {DatabaseContract.MovieColumns.MOVIE_ID};
        Uri uri = DatabaseContract.CONTENT_URI;
        uri = uri.buildUpon().appendPath(id).build();

        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            cursor = getContentResolver().query(uri, projection,
                    selection, selectionArgs, null, null);
        }


        boolean exists = false;
        if (cursor != null) {
            exists = (Objects.requireNonNull(cursor).getCount() > 0);
        }
        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }
}

/* Copyright Satria Junanda */