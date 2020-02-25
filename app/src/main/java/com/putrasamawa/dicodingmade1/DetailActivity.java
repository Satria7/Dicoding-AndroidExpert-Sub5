package com.putrasamawa.dicodingmade1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.putrasamawa.dicodingmade1.db.MovieContract;
import com.putrasamawa.dicodingmade1.db.TVContract;
import com.putrasamawa.dicodingmade1.model.Item;

import java.util.Objects;

/* Copyright Satria Junanda */

public class DetailActivity extends AppCompatActivity {
    private ImageView img, fav;
    private TextView textView1, textView2;
    private ProgressBar progressBar;
    String id, des, imgs, title, type;
    private boolean isfavorite;
    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        Item exampleModel = intent.getParcelableExtra("Model");

        type = getIntent().getStringExtra("key");
        title = exampleModel.getImageResource();
        des = exampleModel.getText1();
        imgs = exampleModel.getText2();
        id = exampleModel.getText3();

        img = findViewById(R.id.image_activity2);
        fav = findViewById(R.id.btn_fav);
        textView1 = findViewById(R.id.text1_activity2);
        textView2 = findViewById(R.id.text2_activity2);
        progressBar = findViewById(R.id.progress);

        if (title == null || des == null || imgs == null) {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, R.string.fail_load, Toast.LENGTH_SHORT).show();
        } else {
            textView1.setText(title);
            textView2.setText(des);
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w342/" + imgs)
                    .into(img);
            Toast.makeText(this, R.string.suc_load, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }

        try {
            if (type.equals("movie")) {

                if (isFavorite(id)) {
                    isfavorite = true;
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit2));
                } else {
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit1));
                }
            } else if (type.equals("tv")) {
                if (isFavorite2(id)) {
                    isfavorite = true;
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit2));
                } else {
                    fav.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.favorit1));
                }
            }
        } catch (Exception e) {

        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("movie")) {
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
                } else if (type.equals("tv")) {
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
                    saveInFavorite2();
                }
            }
        });
    }

    private void saveInFavorite() {
        if (isfavorite) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieContract.MovieColumns.MOVIE_ID, id);
            contentValues.put(MovieContract.MovieColumns.MOVIE_TITLE, title);
            contentValues.put(MovieContract.MovieColumns.MOVIE_IMAGE, imgs);
            contentValues.put(MovieContract.MovieColumns.MOVIE_JENIS, type);
            Uri uri = getContentResolver().insert(MovieContract.CONTENT_URI, contentValues);
            if (uri != null) {
                Log.d(TAG, "Uri " + contentValues);
            }
        } else {
            Uri uri = MovieContract.CONTENT_URI.buildUpon().appendPath(id).build();
            getContentResolver().delete(uri, null, null);
        }
    }

    private boolean isFavorite(String id) {
        Uri uri = MovieContract.CONTENT_URI.buildUpon().appendPath(id).build();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        return cursor.moveToFirst();
    }

    private void saveInFavorite2() {
        if (isfavorite) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TVContract.MovieColumns.MOVIE_ID, id);
            contentValues.put(TVContract.MovieColumns.MOVIE_TITLE, title);
            contentValues.put(TVContract.MovieColumns.MOVIE_IMAGE, imgs);
            contentValues.put(TVContract.MovieColumns.MOVIE_JENIS, type);
            Uri uri = getContentResolver().insert(TVContract.CONTENT_URI, contentValues);
            if (uri != null) {
                Log.d(TAG, "Uri " + contentValues);
            }
        } else {
            Uri uri = TVContract.CONTENT_URI.buildUpon().appendPath(id).build();
            getContentResolver().delete(uri, null, null);
        }
    }

    private boolean isFavorite2(String id) {
        Uri uri = TVContract.CONTENT_URI.buildUpon().appendPath(id).build();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        return cursor.moveToFirst();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void exit(View view) {
        finish();
    }

}

/* Copyright Satria Junanda */