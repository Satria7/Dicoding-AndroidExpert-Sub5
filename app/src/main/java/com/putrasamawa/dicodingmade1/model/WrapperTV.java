package com.putrasamawa.dicodingmade1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/* Copyright Satria Junanda */

@SuppressWarnings("unused")
public class WrapperTV {

    @SerializedName("results")
    private List<ItemTV> results;

    public List<ItemTV> getResults() {
        return results;
    }

    public void setResults(List<ItemTV> results) {
        this.results = results;
    }
}

/* Copyright Satria Junanda */