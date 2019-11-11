package com.example.statsfrommoba;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerProfileStatData {

    @JsonProperty("playername")
    public String playerName;

    @JsonProperty("statname")
    public String statName;

    @JsonProperty("statvalue")
    public String statvalue;
}
