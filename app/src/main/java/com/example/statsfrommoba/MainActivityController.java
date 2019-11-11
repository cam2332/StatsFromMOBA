package com.example.statsfrommoba;

import android.content.Context;
import android.view.View;

public interface MainActivityController {

    default void addProfileStatSmall(String statType, String statValue, String playerName, MainActivityModel.ProfileStatColor headerBackgroundColor) {

    }

    default void setupSearchView() {

    }
}
