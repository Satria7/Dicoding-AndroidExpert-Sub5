package com.putrasamawa.dicodingmade1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/* Copyright Satria Junanda */

@SuppressWarnings("unused")
public class WrapperMovie {

    @SerializedName("results")
    private List<ItemMovie> results;

    public List<ItemMovie> getResults() {
        return results;
    }

    public void setResults(List<ItemMovie> results) {
        this.results = results;
    }
}

/* Copyright Satria Junanda */