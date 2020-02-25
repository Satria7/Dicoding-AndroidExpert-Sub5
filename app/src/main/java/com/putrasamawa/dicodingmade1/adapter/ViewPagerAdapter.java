package com.putrasamawa.dicodingmade1.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/* Copyright Satria Junanda */

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private final List<Fragment> mFragmentList = new ArrayList<>();
	private final List<String> mFragmentTitleList = new ArrayList<>();

	public ViewPagerAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override
	public Fragment getItem(int i) {
		return mFragmentList.get(i);
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	public void addFragment(Fragment fragment, String title) {
		mFragmentList.add(fragment);
		mFragmentTitleList.add(title);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mFragmentTitleList.get(position);
	}
}

/* Copyright Satria Junanda */