package com.example.statsfrommoba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerProfileActivityModel {

    PlayerProfileActivity profileActivity;

    PlayerProfileActivityModel(PlayerProfileActivity profileActivity) {
        this.profileActivity = profileActivity;

        setUpProfileInformation();
    }

    public void setUpProfileInformation() {
        Bundle extras = profileActivity.getIntent().getExtras();
        if(extras == null) {
            return;
        }

        String playerName = (String) extras.get("playername");
        if(playerName != null) {
            //Toast.makeText(profileActivity, "playername" + playerName, Toast.LENGTH_SHORT).show();
            TextView playerNameText = (TextView) profileActivity.findViewById(R.id.profile_playername);
            playerNameText.setText(playerName);
        }

        Integer playerRank = (Integer) extras.get("rank");
        if(playerRank != null) {
            //Toast.makeText(profileActivity, "playerrank" + playerRank, Toast.LENGTH_LONG).show();
            TextView playerRankText = (TextView) profileActivity.findViewById(R.id.profile_rank);
            playerRankText.setText("Rank #" + Integer.toString(playerRank));
        }

        Integer lvl = extras.getInt("lvl");
        TextView lvlText = (TextView) profileActivity.findViewById(R.id.profile_lvl);
        lvlText.setText(Integer.toString(lvl));

        Integer actualxp = (Integer) extras.get("actualxp");
        Integer maxxp = (Integer) extras.get("maxxp");
        ProgressBar progressXp = (ProgressBar) profileActivity.findViewById(R.id.profile_progressbar_xp);
        progressXp.setProgress((actualxp/maxxp)*100);
        System.out.println("PROGRESS : " + Integer.toString(actualxp) + " " + Integer.toString(maxxp));

        TextView progressText = (TextView) profileActivity.findViewById(R.id.profile_progressbar_text);
        progressText.setText(Integer.toString(actualxp) + " / " + Integer.toString(maxxp) + " XP");
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
