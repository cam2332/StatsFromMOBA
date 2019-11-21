package com.example.statsfrommoba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerProfileActivityModel {

    PlayerProfileActivity profileActivity;

    PlayerProfileActivityModel(PlayerProfileActivity profileActivity) {
        this.profileActivity = profileActivity;

        setUpProfileInformation();

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
        String playerName = (String) extras.get("playername");
        TextView playerNameText = (TextView) profileActivity.findViewById(R.id.profile_playername);
        playerNameText.setText(playerName);

        /*
            Rank
         */
        Integer playerRank = (Integer) extras.get("rank");
        TextView playerRankText = (TextView) profileActivity.findViewById(R.id.profile_rank);
        playerRankText.setText("Rank #" + Integer.toString(playerRank));

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
        winPercentText.setText(Double.toString(winPercent));

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
        Double kd = extras.getDouble("kd");
        TextView kdText = profileActivity.findViewById(R.id.profile_kd);
        kdText.setText(Double.toString(kd));

        /*
            KDA
         */
        Double kda = extras.getDouble("kda");
        TextView kdaText = profileActivity.findViewById(R.id.profile_kda);
        kdText.setText(Double.toString(kda));

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
        LinearLayout winsLayout = profileActivity.findViewById(R.id.linearlayout_wins);
        winsLayout.setOnClickListener(v -> {
            Log.d("STAT", "click");
            Toast.makeText(profileActivity, "wins", Toast.LENGTH_SHORT);
            Intent chart = new Intent(profileActivity, ChartActivity.class);
            profileActivity.startActivity(chart);
        });

        LinearLayout winpercentLayout = profileActivity.findViewById(R.id.linearlayout_winpercent);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout killsLayout = profileActivity.findViewById(R.id.linearlayout_kills);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout deathsLayout = profileActivity.findViewById(R.id.linearlayout_deaths);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout kdLayout = profileActivity.findViewById(R.id.linearlayout_kd);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout kdaLayout = profileActivity.findViewById(R.id.linearlayout_kda);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout killspermatchLayout = profileActivity.findViewById(R.id.linearlayout_killspermatch);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout killsperminuteLayout = profileActivity.findViewById(R.id.linearlayout_killsperminute);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout scorepermatchLayout = profileActivity.findViewById(R.id.linearlayout_scorepermatch);
        winsLayout.setOnClickListener(v -> {

        });

        LinearLayout scoreperminuteLayout = profileActivity.findViewById(R.id.linearlayout_scoreperminute);
        winsLayout.setOnClickListener(v -> {

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
}
