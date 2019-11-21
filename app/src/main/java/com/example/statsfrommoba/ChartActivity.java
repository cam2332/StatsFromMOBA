package com.example.statsfrommoba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.util.Arrays;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        WebView chart = findViewById(R.id.chart);
        chart.getSettings().setJavaScriptEnabled(true);
        chart.requestFocusFromTouch();

        String[][] data = {
                {"'Date'", "'wins'"},
                {"'2010'", "3"},
                {"'2011'", "5"},
                {"'2012'", "7"},
                {"'2013'", "6"},
                {"'2014'", "2"},
        };
        JSHtmlEditor html = new JSHtmlEditor(data,ChartTypes.COLUMNCHART);

        chart.loadData(html.getWebPage(),"text/html", null);
    }
}
