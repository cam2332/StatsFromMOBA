package com.example.statsfrommoba;

import android.content.Context;

public interface MainActivityController {

    void addProfileStatSmall(String statType, String statValue, String playerName, MainActivityModel.ProfileStatColor headerBackgroundColor);

    void setupSearchView();
}
