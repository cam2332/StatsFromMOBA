package com.example.statsfrommoba;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityModel {

    public enum ProfileStatColor {
        BLUE, GREEN, PURPLE, YELLOW;
    }
    MainActivity activity;

    public MainActivityModel(MainActivity activity){
        this.activity = activity;

        Toolbar myToolbar = (Toolbar) activity.findViewById(R.id.top_toolbar);
        activity.setSupportActionBar(myToolbar);
        this.activity.getWindow().setStatusBarColor(this.activity.getResources().getColor(R.color.toolbarColor, this.activity.getTheme()));

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() == null) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            alertDialog.setTitle("No Internet Connection");
            alertDialog.setMessage("Please check your internet connection and try again");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        activity.finishAffinity();
                        System.exit(0);
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Restart App",
                    (dialogInterface, i) -> {
                        Intent intent = activity.getPackageManager()
                                .getLaunchIntentForPackage(activity.getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                        ActivityCompat.finishAfterTransition(activity);
                    });
            alertDialog.show();
        } else {
            setupSearchView();

            AsyncTask.execute(() -> {
            /*
                Create card with info about best player
             */
                PlayerProfileStatData bestPlayer = RESTConnector.getPlayerProfileStatData("stats/ranking/best/bestplayer");
                activity.runOnUiThread(() -> {
                    ImageView image = addProfileStatSmall("Najlepszy gracz", bestPlayer.statvalue, bestPlayer.playerName, MainActivityModel.ProfileStatColor.BLUE);
                    /* Get player profile image */
                    new DownloadImageTask1(image).execute("https://api.adorable.io/avatars/60/" + bestPlayer.playerName + ".png");});

            /*
                Create card with info about most wins
             */
                PlayerProfileStatData mostWins = RESTConnector.getPlayerProfileStatData("stats/ranking/best/mostwins");
                activity.runOnUiThread(() -> {
                    ImageView image = addProfileStatSmall("Najwięcej zwycięstw", mostWins.statvalue, mostWins.playerName, MainActivityModel.ProfileStatColor.YELLOW);
                    /* Get player profile image */
                    new DownloadImageTask1(image).execute("https://api.adorable.io/avatars/60/" + mostWins.playerName + ".png");});

            /*
                Create card with info about most kills
             */
                PlayerProfileStatData mostKills = RESTConnector.getPlayerProfileStatData("stats/ranking/best/mostkills");
                activity.runOnUiThread(() -> {
                    ImageView image = addProfileStatSmall("Najwięcej likwidacji", mostKills.statvalue, mostKills.playerName, MainActivityModel.ProfileStatColor.PURPLE);
                    /* Get player profile image */
                    new DownloadImageTask1(image).execute("https://api.adorable.io/avatars/60/" + mostKills.playerName + ".png");});
            });

            TextView title = activity.findViewById(R.id.main_title);
            title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    App.darkMode = !App.darkMode;
                    Toast.makeText(activity, "DARK MODE: " + App.darkMode, Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }
    }


    public ImageView addProfileStatSmall(String statType, String statValue, final String playerName, ProfileStatColor headerBackgroundColor) {
        LinearLayout stats = (LinearLayout) activity.findViewById(R.id.main_activity_linear_layout_main_stats);
        View child = activity.getLayoutInflater().inflate(R.layout.player_profilestat_small,null);

        LinearLayout header = (LinearLayout) child.findViewById(R.id.player_profilestat_small_header);
        Drawable background = ContextCompat.getDrawable(activity,backgroundDrawable(headerBackgroundColor));
        header.setBackground(background);

        ImageView imageView = (ImageView) child.findViewById(R.id.player_profilestat_small_profile_image);
        //imageView.setBorderColor(ContextCompat.getColor(activity,lightColor(headerBackgroundColor)));

        TextView childPlayerName = (TextView) child.findViewById(R.id.player_profilestat_small_textview_player_name);
        childPlayerName.setText(playerName);
        TextView childStatType = (TextView) child.findViewById(R.id.player_profilestat_small_textview_stat_type);
        childStatType.setText(statType);
        TextView childStatValue = (TextView) child.findViewById(R.id.player_profilestat_small_textview_stat_value);
        childStatValue.setText(statValue);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(40,30,40,30);
        stats.addView(child, layoutParams);

        LinearLayout body = (LinearLayout) child.findViewById(R.id.player_profilestat_small_body);
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(() -> {
                    PlayerProfileData profileData = RESTConnector.getPlayerProfileData(playerName);
                    activity.runOnUiThread(() -> {
                        Intent playerProfileScreen = new Intent(activity,PlayerProfileActivity.class);
                        playerProfileScreen.putExtras(PlayerProfileActivityModel.getPlayerProfileBundle(profileData));
                        switch (headerBackgroundColor) {
                            case BLUE:

                                playerProfileScreen.putExtra("darkColor", Integer.toHexString(R.color.blueDark));
                                break;
                            case GREEN:
                                playerProfileScreen.putExtra("color", Integer.toHexString(R.color.greenLight));
                                playerProfileScreen.putExtra("darkColor", Integer.toHexString(R.color.greenDark));
                                break;
                            case PURPLE:
                                playerProfileScreen.putExtra("color", Integer.toHexString(R.color.purpleLight));
                                playerProfileScreen.putExtra("darkColor", Integer.toHexString(R.color.purpleDark));
                                break;
                            case YELLOW:
                                playerProfileScreen.putExtra("color", Integer.toHexString(R.color.yellowLight));
                                playerProfileScreen.putExtra("darkColor", Integer.toHexString(R.color.yellowDark));
                                break;
                        }
                        playerProfileScreen.putExtra("color", headerBackgroundColor.toString());
                        activity.startActivity(playerProfileScreen);
                    });
                });
            }
        });
        return imageView;
    }


    public void setupSearchView() {
        /*
         SearchView
         */
        SearchView searchView = (SearchView) activity.findViewById(R.id.main_search_view);
        searchView.setActivated(true);
        searchView.setQueryHint(App.getAppResources().getString(R.string.search_view_query_hint));
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        setSearchViewOnClickListener(searchView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchScreen = new Intent(activity,SearchPlayersActivity.class);
                activity.startActivityForResult(searchScreen,0);
            }
        });
    }

    private static void setSearchViewOnClickListener(View v, View.OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)v;
            int count = group.getChildCount();
            for ( int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }
                if (child instanceof TextView) {
                    TextView text = (TextView)child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }

    public int backgroundDrawable(ProfileStatColor color) {
        switch (color) {
            case BLUE: return R.drawable.two_color_angle_bluebackground;
            case GREEN: return R.drawable.two_color_angle_greenbackground;
            case PURPLE: return R.drawable.two_color_angle_purplebackground;
            case YELLOW: return R.drawable.two_color_angle_yellowbackground;
            default: return -1;
        }
    }

    public int lightColor(ProfileStatColor color) {
        switch (color) {
            case BLUE: return R.color.blueLight;
            case GREEN: return R.color.greenLight;
            case PURPLE: return R.color.purpleLight;
            case YELLOW: return R.color.yellowLight;
            default: return -1;
        }
    }

    public int darkColor(ProfileStatColor color) {
        switch (color) {
            case BLUE: return R.color.blueDark;
            case GREEN: return R.color.greenDark;
            case PURPLE: return R.color.purpleDark;
            case YELLOW: return R.color.yellowDark;
            default: return -1;
        }
    }

    public class DownloadImageTask1 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask1(ImageView bmImage) {
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
