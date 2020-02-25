package com.putrasamawa.dicodingmade1.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/* Copyright Satria Junanda */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}

/* Copyright Satria Junanda */