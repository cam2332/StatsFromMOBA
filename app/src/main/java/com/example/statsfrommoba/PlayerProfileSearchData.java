package com.example.statsfrommoba;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerProfileSearchData {

    @JsonProperty("playername")
    public String playerName;

    @JsonProperty("rank")
    public String rank;

    @NonNull
    @Override
    public String toString() {
        return "playerName: " + playerName + " , rank: " + rank;
    }
}
