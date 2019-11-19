package com.example.statsfrommoba;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerProfileData {

    @JsonProperty("playername")
    public String playerName;

    @JsonProperty("rank")
    public Integer rank;

    @JsonProperty("lvl")
    public Integer lvl;

    @JsonProperty("actualxp")
    public Integer actualXP;

    @JsonProperty("maxxp")
    public Integer maxXP;

    @JsonProperty("wins")
    public Integer wins;

    @JsonProperty("winpercent")
    public Double winPercent;

    @JsonProperty("kills")
    public Integer kills;

    @JsonProperty("deaths")
    public Integer deaths;

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
