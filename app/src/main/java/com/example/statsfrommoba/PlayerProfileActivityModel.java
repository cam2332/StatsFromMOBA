package com.example.statsfrommoba;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerProfileActivityModel {

    PlayerProfileActivity profileActivity;
    String playerName;

    PlayerProfileActivityModel(PlayerProfileActivity profileActivity) {
        this.profileActivity = profileActivity;

        Toolbar myToolbar = (Toolbar) this.profileActivity.findViewById(R.id.top_toolbar);
        this.profileActivity.setSupportActionBar(myToolbar);
        this.profileActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {

            if(App.darkMode) {
                ((TextView)profileActivity.findViewById(R.id.profile_playername)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_rank)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_lvl)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_lvl_text)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_wins)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_winpercent)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_kills)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_deaths)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_kd)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_kda)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_killspermatch)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_killsperminute)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_scorepermatch)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
                ((TextView)profileActivity.findViewById(R.id.profile_scoreperminute)).setTextColor(profileActivity.getResources().getColor(R.color.backgroundblack,null));
            }

            Bundle extras = profileActivity.getIntent().getExtras();
            String color = (String) extras.get("color");
            switch (MainActivityModel.ProfileStatColor.valueOf(color)) {
                case BLUE:
                    this.profileActivity.getWindow().setStatusBarColor(Color.parseColor("#1B70A5"));
                    if(App.darkMode) {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_blueblackbackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                    } else {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_bluewhitebackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));
                    }
                    profileActivity.findViewById(R.id.top_toolbar).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueDark, null));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.blueDark, null)));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressBackgroundTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.blueLight, null)));
                    ((CardView) profileActivity.findViewById(R.id.profile_image_background)).setCardBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.profile_divider_above_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.profile_divider_under_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));

                    profileActivity.findViewById(R.id.divider2).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider3).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider4).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider5).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider6).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider7).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider8).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider9).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider10).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    profileActivity.findViewById(R.id.divider11).setBackgroundColor(profileActivity.getResources().getColor(R.color.blueLight, null));
                    break;
                case GREEN:
                    this.profileActivity.getWindow().setStatusBarColor(Color.parseColor("#0A9600"));
                    break;
                case PURPLE:
                    this.profileActivity.getWindow().setStatusBarColor(Color.parseColor("#6335B6"));
                    if(App.darkMode) {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_purpleblackbackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                    } else {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_purplewhitebackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));
                    }
                    profileActivity.findViewById(R.id.top_toolbar).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleDark, null));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.purpleDark, null)));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressBackgroundTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.purpleLight, null)));
                    ((CardView) profileActivity.findViewById(R.id.profile_image_background)).setCardBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.profile_divider_above_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.profile_divider_under_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));

                    profileActivity.findViewById(R.id.divider2).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider3).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider4).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider5).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider6).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider7).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider8).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider9).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider10).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    profileActivity.findViewById(R.id.divider11).setBackgroundColor(profileActivity.getResources().getColor(R.color.purpleLight, null));
                    break;
                case YELLOW:
                    this.profileActivity.getWindow().setStatusBarColor(Color.parseColor("#C5A400"));
                    if(App.darkMode) {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_yellowblackbackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundblack, null));
                    } else {
                        profileActivity.findViewById(R.id.profile_background).setBackground(profileActivity.getResources().getDrawable(R.drawable.two_color_horizon_yellowwhitebackground, null));
                        profileActivity.findViewById(R.id.profile_lvl_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));
                        profileActivity.findViewById(R.id.profile_stats_background).setBackground(profileActivity.getResources().getDrawable(R.color.backgroundwhite, null));

                    }
                    profileActivity.findViewById(R.id.top_toolbar).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowDark, null));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.yellowDark, null)));
                    ((ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp)).setProgressBackgroundTintList(ColorStateList.valueOf(profileActivity.getResources().getColor(R.color.yellowLight, null)));
                    ((CardView) profileActivity.findViewById(R.id.profile_image_background)).setCardBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.profile_divider_above_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.profile_divider_under_lvl).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));

                    profileActivity.findViewById(R.id.divider2).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider3).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider4).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider5).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider6).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider7).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider8).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider9).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider10).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    profileActivity.findViewById(R.id.divider11).setBackgroundColor(profileActivity.getResources().getColor(R.color.yellowLight, null));
                    break;
            }

            Toast.makeText(profileActivity, color, Toast.LENGTH_LONG).show();
            String darkColor = (String) extras.get("darkColor");
        } catch (Exception e) {

        }

        setUpProfileInformation();

//        LinearLayout winsLayout =(LinearLayout) profileActivity.findViewById(R.id.linearlayout_wins);
//        winsLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("STAT", "click");
//                Toast.makeText(profileActivity, "wins", Toast.LENGTH_SHORT).show();
//            }
//        });
        setUpStatsClickEvents();
    }

    public void setUpProfileInformation() {
        Bundle extras = profileActivity.getIntent().getExtras();
        if(extras == null) {
            return;
        }
        /*
            Player Name
         */
        playerName = (String) extras.get("playername");
        TextView playerNameText = (TextView) profileActivity.findViewById(R.id.profile_playername);
        playerNameText.setText(playerName);

        /* Get player profile image */
        new DownloadImageTask((ImageView) profileActivity.findViewById(R.id.profile_rounded_image)).execute("https://api.adorable.io/avatars/100/" + playerName + ".png");

        /*
            Rank
         */
        Integer playerRank = (Integer) extras.get("rank");
        TextView playerRankText = (TextView) profileActivity.findViewById(R.id.profile_rank);
        playerRankText.setText(App.getAppResources().getString(R.string.profile_rank) + " #" + Integer.toString(playerRank));

        /*
            Lvl
         */
        Integer lvl = extras.getInt("lvl");
        TextView lvlText = (TextView) profileActivity.findViewById(R.id.profile_lvl);
        lvlText.setText(Integer.toString(lvl));

        /*
            XP
         */
        Integer actualxp = (Integer) extras.get("actualxp");
        Integer maxxp = (Integer) extras.get("maxxp");
        ProgressBar progressXp = (ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp);
        double progress = (double)actualxp/maxxp;
        progress *= 100;
        progressXp.setProgress((int)progress);

        TextView progressText = (TextView) profileActivity.findViewById(R.id.profile_progressbar_text);
        progressText.setText(Integer.toString(actualxp) + " / " + Integer.toString(maxxp) + " XP");

        /*
            Wins
         */
        Integer wins = extras.getInt("wins");
        TextView winsText = profileActivity.findViewById(R.id.profile_wins);
        winsText.setText(Integer.toString(wins));

        /*
            Win percent
         */
        Double winPercent = extras.getDouble("winpercent");
        TextView winPercentText = profileActivity.findViewById(R.id.profile_winpercent);
        winPercentText.setText(Double.toString(winPercent) + "%");

        /*
            Kills
         */
        Integer kills = extras.getInt("kills");
        TextView killsText = profileActivity.findViewById(R.id.profile_kills);
        killsText.setText(Integer.toString(kills));

        /*
            Deaths
         */
        Integer deaths = extras.getInt("deaths");
        TextView deathsText = profileActivity.findViewById(R.id.profile_deaths);
        deathsText.setText(Integer.toString(deaths));

        /*
            KD
         */
        Double kd = Math.round(extras.getDouble("kd") * 100.0) / 100.0;
        TextView kdText = profileActivity.findViewById(R.id.profile_kd);
        kdText.setText(Double.toString(kd));

        /*
            KDA
         */
        Double kda = Math.round(extras.getDouble("kda") * 100.0) /100.0;
        TextView kdaText = profileActivity.findViewById(R.id.profile_kda);
        kdaText.setText(Double.toString(kda));

        /*
            Kills per match
         */
        Double killsPerMatch = Math.round(extras.getDouble("killspermatch") * 100.0) / 100.0;
        TextView killsPerMatchText = profileActivity.findViewById(R.id.profile_killspermatch);
        killsPerMatchText.setText(Double.toString(killsPerMatch));

        /*
            Kills per minute
         */
        Double killsPerMinute = Math.round(extras.getDouble("killsperminute") * 100.0) / 100.0;
        TextView killsPerMinuteText = profileActivity.findViewById(R.id.profile_killsperminute);
        killsPerMinuteText.setText(Double.toString(killsPerMinute));

        /*
            Score per match
         */
        Double scorePerMatch = extras.getDouble("scorepermatch");
        TextView scorePerMatchText = profileActivity.findViewById(R.id.profile_scorepermatch);
        scorePerMatchText.setText(Double.toString(scorePerMatch));

        /*
            Score per minute
         */
        Double scorePerMinute = extras.getDouble("scoreperminute");
        TextView scorePerMinuteText = profileActivity.findViewById(R.id.profile_scoreperminute);
        scorePerMinuteText.setText(Double.toString(scorePerMinute));
    }

    public void setUpStatsClickEvents() {
        LinearLayout winsLayout =(LinearLayout) profileActivity.findViewById(R.id.linearlayout_wins);
        winsLayout.setOnClickListener(v -> {
            Log.d("STAT", "click");
            Toast.makeText(profileActivity, "wins", Toast.LENGTH_SHORT).show();

            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "wins_losses");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Wins'","'Losses'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "wins");
                    profileActivity.startActivity(chart);
                });
            });
        });


        LinearLayout winpercentLayout = profileActivity.findViewById(R.id.linearlayout_winpercent);
        winpercentLayout.setOnClickListener(v -> {
            Log.d("STAT", "click");
            Toast.makeText(profileActivity, "winpercent", Toast.LENGTH_SHORT).show();

            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "wins_percent");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Wins percent'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "wins_percent");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout killsLayout = profileActivity.findViewById(R.id.linearlayout_kills);
        killsLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "kills");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Kills'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "kills");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout deathsLayout = profileActivity.findViewById(R.id.linearlayout_deaths);
        deathsLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "deaths");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Deaths'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "deaths");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout kdLayout = profileActivity.findViewById(R.id.linearlayout_kd);
        kdLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "kd");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Kills'","'Deaths'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "kd");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout kdaLayout = profileActivity.findViewById(R.id.linearlayout_kda);
        kdaLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "kda");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'","'Assists'", "'Kills'","'Deaths'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "kda");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout killspermatchLayout = profileActivity.findViewById(R.id.linearlayout_killspermatch);
        killspermatchLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "kills_per_match");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Kills per match'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "kills_per_match");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout killsperminuteLayout = profileActivity.findViewById(R.id.linearlayout_killsperminute);
        killsperminuteLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "kills_per_minute");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Kills per minute'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "kills_per_minute");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout scorepermatchLayout = profileActivity.findViewById(R.id.linearlayout_scorepermatch);
        scorepermatchLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "score_per_match");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Score per match'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "score_per_match");
                    profileActivity.startActivity(chart);
                });
            });
        });

        LinearLayout scoreperminuteLayout = profileActivity.findViewById(R.id.linearlayout_scoreperminute);
        scoreperminuteLayout.setOnClickListener(v -> {
            AsyncTask.execute(() -> {
                JsonNode profileHistoryData = RESTConnector.getPlayerProfileHistoryDate(playerName, "score_per_minute");
                profileActivity.runOnUiThread(() -> {
                    Intent chart = new Intent(profileActivity, ChartActivity.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<ArrayList<String>> data2d = mapper.convertValue(profileHistoryData.get("lastmonth"),ArrayList.class);
                    data2d.add(0,new ArrayList<>(Arrays.asList("'Date'", "'Score per minute'")));
                    Log.d("PROFILE CHART", profileHistoryData.asText());
                    Log.d("PROFILE CHART 2D ARRAY", data2d.get(0).toString());
                    chart.putExtra("lastmonth", data2d);
                    chart.putExtra("stattype", "score_per_minute");
                    profileActivity.startActivity(chart);
                });
            });
        });
    }

    public static Bundle getPlayerProfileBundle(PlayerProfileData p) {
        Bundle b = new Bundle();

        b.putString("playername", p.playerName);
        b.putInt("rank", p.rank);
        b.putInt("lvl", p.lvl);
        b.putInt("actualxp", p.actualXP);
        b.putInt("maxxp", p.maxXP);
        b.putInt("wins", p.wins);
        b.putDouble("winpercent", p.winPercent);
        b.putInt("kills", p.kills);
        b.putInt("deaths",p.deaths);
        b.putDouble("kd", p.kd);
        b.putDouble("kda", p.kda);
        b.putDouble("killspermatch", p.killsPerMatch);
        b.putDouble("killsperminute", p.killsPerMinute);
        b.putDouble("scorepermatch", p.scorePerMatch);
        b.putDouble("scoreperminute", p.scorePerMinute);

        return b;
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
