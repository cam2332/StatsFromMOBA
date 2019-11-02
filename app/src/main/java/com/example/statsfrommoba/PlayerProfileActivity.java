package com.example.statsfrommoba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerProfileActivity extends AppCompatActivity {

    private ImageView imageViewHexagon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpProfileInformation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    private void setUpProfileInformation() {
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        String playerName = (String) extras.get("player_name");
        if(playerName != null) {
            Toast.makeText(PlayerProfileActivity.this, "player_name" + playerName, Toast.LENGTH_SHORT).show();
            TextView playerNameText = (TextView) findViewById(R.id.textView_playerName);
            playerNameText.setText(playerName);
        }

        String playerRank = (String) extras.get("player_rank");
        if(playerRank != null) {
            Toast.makeText(PlayerProfileActivity.this, "player_rank" + playerRank, Toast.LENGTH_LONG).show();
            TextView playerRankText = (TextView) findViewById(R.id.textView_playerRank);
            playerRankText.setText("Rank #" + playerRank);
        }
    }
}
