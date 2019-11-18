package com.example.statsfrommoba;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerProfileSearchData {

    @JsonProperty("playername")
    public String playerName;

    @JsonProperty("rank")
    public String rank;
}
