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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.adapter.AdapterMovie;
import com.putrasamawa.dicodingmade1.db.MovieContract;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemMovie;
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

public class MovieFavFragment extends Fragment {
	private RecyclerView rey;
	private TextView progressBar;
	AdapterMovie movieAdapter;
	List<ItemMovie> movieList;
	RestApiService movieService;
	Call<ItemMovie> movieCall;
	Call<ItemMovie> movieFavoriteCall;
	ItemMovie movieResult;

	public MovieFavFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_movie_fav, container, false);
		ButterKnife.bind(this, rootView);
		rey = rootView.findViewById(R.id.list_fav_mov);
		progressBar = rootView.findViewById(R.id.myprogress);
		movieList = new ArrayList<>();
		movieResult = new ItemMovie();
		ArrayList<Item> movieFavoriteArrayList = new ArrayList<>();
		Cursor cursor = getActivity().getContentResolver().query(MovieContract.CONTENT_URI, null, null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			do {
				movieFavoriteArrayList.add(
						new Item
								(cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieColumns.MOVIE_ID)),
										cursor.getString(cursor.getColumnIndexOrThrow(MovieContract.MovieColumns.MOVIE_JENIS))));
				cursor.moveToNext();
			} while (!cursor.isAfterLast());
		}
		for (int i = 0; i < movieFavoriteArrayList.size(); i++) {
			if (((Item) movieFavoriteArrayList.get(i)).getJenis().equals("movie")) {
				getFavoriteMovies(((Item) movieFavoriteArrayList.get(i)).getText3());
			}
		}
		initView();

		return rootView;
	}
	private void initView() {
		movieAdapter = new AdapterMovie(getContext());
		rey.setLayoutManager(new GridLayoutManager(getContext(), 2));
		rey.setHasFixedSize(true);
		rey.setItemAnimator(new DefaultItemAnimator());
	}

	private void getFavoriteMovies(String id) {
		movieService = RetrofitInstance.getApiService();
		movieFavoriteCall = movieService.getMovieById(id, "cf72efb6e9eb91453d9aabf8a9d16ae8");

		movieFavoriteCall.enqueue(new Callback<ItemMovie>() {
			@Override
			public void onResponse(@NonNull Call<ItemMovie> call, @NonNull Response<ItemMovie> response) {
				movieList.add(response.body());
				movieAdapter.setmMovieList(movieList);
				rey.setAdapter(movieAdapter);
				progressBar.setVisibility(View.GONE);

			}

			@Override
			public void onFailure(@NonNull Call<ItemMovie> call, @NonNull Throwable t) {
				movieResult = null;
				progressBar.setVisibility(View.GONE);
			}
		});
	}
}

/* Copyright Satria Junanda */