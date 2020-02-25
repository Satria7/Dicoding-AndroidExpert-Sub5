package com.putrasamawa.dicodingmade1.fragment;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.adapter.AdapterTV;
import com.putrasamawa.dicodingmade1.db.TVContract;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemTV;
import com.putrasamawa.dicodingmade1.service.RestApiService;
import com.putrasamawa.dicodingmade1.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* Copyright Satria Junanda */

public class TvShowFavFragment extends Fragment {
	private RecyclerView rey;
	private TextView progressBar;
	AdapterTV movieAdapter;
	List<ItemTV> movieList;
	RestApiService movieService;
	Call<ItemTV> movieCall;
	Call<ItemTV> movieFavoriteCall;
	ItemTV movieResult;
	public TvShowFavFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
		ButterKnife.bind(this, rootView);
		rey = rootView.findViewById(R.id.list_fav_mov);
		progressBar = rootView.findViewById(R.id.myprogress);

		movieList = new ArrayList<>();
		movieResult = new ItemTV();

		ArrayList<Item> movieFavoriteArrayList = new ArrayList<>();
		Cursor cursor = getActivity().getContentResolver().query(TVContract.CONTENT_URI, null, null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			do {
				movieFavoriteArrayList.add(
						new Item
								(cursor.getString(cursor.getColumnIndexOrThrow(TVContract.MovieColumns.MOVIE_ID)),
										cursor.getString(cursor.getColumnIndexOrThrow(TVContract.MovieColumns.MOVIE_JENIS))));
				cursor.moveToNext();
			} while (!cursor.isAfterLast());
		}
		for (int i = 0; i < movieFavoriteArrayList.size(); i++) {
			if (((Item) movieFavoriteArrayList.get(i)).getJenis().equals("tv")) {
				getFavoriteMovies(((Item) movieFavoriteArrayList.get(i)).getText3());
			}
		}
		initView();

		return rootView;
	}

	private void initView() {
		movieAdapter = new AdapterTV(getContext());
		rey.setLayoutManager(new GridLayoutManager(getContext(), 2));
		rey.setHasFixedSize(true);
		rey.setItemAnimator(new DefaultItemAnimator());
	}

	private void getFavoriteMovies(String id) {
		movieService = RetrofitInstance.getApiService();
		movieFavoriteCall = movieService.getTVById(id, "cf72efb6e9eb91453d9aabf8a9d16ae8");

		movieFavoriteCall.enqueue(new Callback<ItemTV>() {
			@Override
			public void onResponse(@NonNull Call<ItemTV> call, @NonNull Response<ItemTV> response) {
				movieList.add(response.body());
				movieAdapter.setmTVList(movieList);
				rey.setAdapter(movieAdapter);
				progressBar.setVisibility(View.GONE);


			}

			@Override
			public void onFailure(@NonNull Call<ItemTV> call, @NonNull Throwable t) {
				movieResult = null;
				progressBar.setVisibility(View.GONE);
			}
		});
	}
}

/* Copyright Satria Junanda */