package com.example.statsfrommoba;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class ChartJSInterface {

    private WebView mAppView;

    public ChartJSInterface (WebView appView) {
        mAppView = appView;
    }

    @JavascriptInterface
    public void swipeLeft() {

    }

    @JavascriptInterface
    public void swipeRight() {

    }
}
