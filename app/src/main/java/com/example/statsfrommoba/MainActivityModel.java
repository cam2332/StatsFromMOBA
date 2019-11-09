package com.example.statsfrommoba;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityModel implements MainActivityController {

    MainActivity activity;

    public MainActivityModel(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void addProfileStatSmall(String statType, String statValue, String playerName) {
        LinearLayout stats = (LinearLayout) activity.findViewById(R.id.main_activity_linear_layout_main_stats);
        View child = activity.getLayoutInflater().inflate(R.layout.player_profilestat_small,null);
        TextView childPlayerName = (TextView) child.findViewById(R.id.player_profilestat_small_textview_player_name);
        childPlayerName.setText(playerName);
        TextView childStatType = (TextView) child.findViewById(R.id.player_profilestat_small_textview_stat_type);
        childStatType.setText(statType);
        TextView childStatValue = (TextView) child.findViewById(R.id.player_profilestat_small_textview_stat_value);
        childStatValue.setText(statValue);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(40,10,40,10);
        stats.addView(child, layoutParams);
    }
}
