package com.example.statsfrommoba;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerProfileHistoryData {

    @JsonProperty("wins_losses")
    WinsLosses wins_losses;

    public static class WinsLosses {

        @JsonProperty("lastday")
        LastDay[] lastDays;

        public static class LastDay {

            @JsonProperty("datetime")
            String datetime;

            @JsonProperty("wins")
            Integer wins;

            @JsonProperty("losses")
            Integer losses;
        }

    }
}
