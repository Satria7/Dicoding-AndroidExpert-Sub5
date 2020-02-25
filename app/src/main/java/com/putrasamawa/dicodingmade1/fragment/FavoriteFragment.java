package com.putrasamawa.dicodingmade1.fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/* Copyright Satria Junanda */

public class FavoriteFragment extends Fragment {

	@BindView(R.id.viewpager)
	ViewPager viewPager;
	@BindView(R.id.tabs)
	TabLayout tabLayout;

	public FavoriteFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
		ButterKnife.bind(this, rootView);
		setupViewPager(viewPager);
		tabLayout.setupWithViewPager(viewPager);
		return rootView;
	}

	private void setupViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
		adapter.addFragment(new MovieFavFragment(), getResources().getString(R.string.tab_text_1));
		adapter.addFragment(new TvShowFavFragment(), getResources().getString(R.string.tab_text_2));
		viewPager.setAdapter(adapter);
	}

}

/* Copyright Satria Junanda */