package com.example.statsfrommoba;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class App extends Application {
    private static App singleton;
    private static Resources resources;

    public static Boolean darkMode = Boolean.valueOf(false);

    public App getInstence() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        resources = getResources();

        //Intent mainActivity = new Intent(App.this,MainActivity.class);
        //startActivity(mainActivity);
    }

    public static Resources getAppResources() {
        return resources;
    }
}
