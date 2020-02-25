package com.putrasamawa.dicodingmade1.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.putrasamawa.dicodingmade1.SearchActivity;
import com.putrasamawa.dicodingmade1.adapter.AdapterTV;
import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.model.ItemTV;
import com.putrasamawa.dicodingmade1.viewmodel.ViewModelTV;

import java.util.List;

/* Copyright Satria Junanda */

public class FragmentTV extends Fragment {
    RecyclerView rey;
    ProgressBar progressBar;
    private ViewModelTV mainViewModel;
    AdapterTV adapter;
    private GridLayoutManager layout;
    ImageView searchView;
    CardView cardView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment, container, false);
        rey = root.findViewById(R.id.recyclerView);
        searchView = root.findViewById(R.id.icon_cari);
        cardView = root.findViewById(R.id.card_anim);
        progressBar = root.findViewById(R.id.myprogress);

        mainViewModel = ViewModelProviders.of(this).get(ViewModelTV.class);
        getPopularTV();

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCari();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCari();
            }
        });

        return root;
    }

    private void goCari() {
        Intent cari = new Intent(getContext(), SearchActivity.class);
        cari.putExtra("key", "tv");
        startActivity(cari);
    }

    private void getPopularTV() {
        mainViewModel.getAll().observe(this, new Observer<List<ItemTV>>() {
            @Override
            public void onChanged(@Nullable List<ItemTV> blogList) {
                prepareRecyclerView(blogList);
                showLoading(false);
            }
        });
    }


    private void prepareRecyclerView(List<ItemTV> movieList) {

        adapter = new AdapterTV(movieList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rey.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            rey.setLayoutManager(new GridLayoutManager(getContext(), 3));

        }
        rey.setItemAnimator(new DefaultItemAnimator());
        rey.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        showLoading(false);

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

/* Copyright Satria Junanda */