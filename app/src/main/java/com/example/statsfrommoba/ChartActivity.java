package com.example.statsfrommoba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        WebView chart = findViewById(R.id.chart);
        chart.getSettings().setJavaScriptEnabled(true);
        chart.requestFocusFromTouch();
        //chart.addJavascriptInterface(new ChartJSInterface(chart), "Android");

        String[][] data = {
                {"'Date'", "'wins'","'losses'"},
                {"'2010'", "3", "-2"},
                {"'2011'", "5", "0"},
                {"'2012'", "7", "-4"},
                {"'2013'", "6", "-1"},
                {"'2014'", "2", "-3"},
                {"'2015'", "2", "0"},
                {"'2016'", "9", "-2"},
                {"'2017'", "5", "-6"},
                {"'2018'", "8", "-3"},
        };
        String[][] data1 = {
                {"'Day'", "'wins'","'losses'"},
                {"'16.11.2019'", "2", "-4"},
                {"'17.11.2019'", "7", "-4"},
                {"'18.11.2019'", "6", "-1"},
                {"'19.11.2019'", "2", "-3"},
                {"'20.11.2019'", "2", "-0"},
                {"'21.11.2019'", "9", "-2"},
                {"'22.11.2019'", "5", "-6"},
        };

        String [] colors = {
                "#1e88e5",
                "#e4002b",
        };
        HtmlChartMaker html = new HtmlChartMaker(data, data1, data, data1, data,ChartTypes.COLUMNCHART, true, colors);

        chart.loadData(html.getWebPage(),"text/html", null);

        chart.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Toast.makeText(ChartActivity.this,"Swipe Left", Toast.LENGTH_SHORT).show();
                //chart.loadUrl("javascript:nextChart()");
                //chart.loadUrl("javascript:chart.draw(data[current], options)");
                chart.evaluateJavascript("javascript:nextChart();", null);
            }
            @Override
            public void onSwipeRight() {
                Toast.makeText(ChartActivity.this,"Swipe Right", Toast.LENGTH_SHORT).show();
                chart.evaluateJavascript("javascript:previousChart();", null);
            }
        });
    }
}
