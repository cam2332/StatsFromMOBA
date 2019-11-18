package com.example.statsfrommoba;

import android.os.Bundle;

public class PlayerProfileActivityModel {

    public static Bundle getPlayerProfileBundle(PlayerProfileData p) {
        Bundle b = new Bundle();

        b.putString("playername", p.playerName);
        b.putInt("rank", p.rank);
        b.putInt("lvl", p.lvl);

        return b;
    }
}
