package com.example.statsfrommoba;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerProfileData {

    @JsonProperty("playername")
    public String playerName;

    @JsonProperty("lvl")
    public Integer lvl;

    @JsonProperty("actualxp")
    public Integer actualXP;

    @JsonProperty("maxxp")
    public Integer maxXP;

    @JsonProperty("playedmatches")
    public Integer playedMatches;

    @JsonProperty("wins")
    public Integer wins;

    @JsonProperty("winpercent")
    public Double winPercent;

    @JsonProperty("kills")
    public Integer kills;

    @JsonProperty("deaths")
    public Integer deaths;

    @JsonProperty("assists")
    public Integer assists;

    @JsonProperty("kd")
    public Double kd;

    @JsonProperty("kda")
    public Double kda;

    @JsonProperty("killspermatch")
    public Double killsPerMatch;

    @JsonProperty("killsperminute")
    public Double killsPerMinute;

    @JsonProperty("scorepermatch")
    public Integer scorePerMatch;

    @JsonProperty("scoreperminute")
    public Integer scorePerMinute;
}
