package com.example.statsfrommoba;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;

public class App extends Application {
    private static App singleton;
    private static Resources resources;

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
