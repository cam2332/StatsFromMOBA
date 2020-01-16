package com.example.statsfrommoba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        WebView chart = findViewById(R.id.chart);
        chart.getSettings().setJavaScriptEnabled(true);
        chart.requestFocusFromTouch();
        chart.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
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
        ArrayList<ArrayList<String>> adata =new ArrayList<>(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList("'Date'", "'wins'","'losses'")),
                        new ArrayList<>(Arrays.asList("'16.11.2019'", "2", "-4"))
                        )
        );
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

        ChartTypes chartType = ChartTypes.COLUMNCHART;
        Bundle extras = getIntent().getExtras();
        ArrayList<String> lcolors = new ArrayList<>();
        switch ((String) extras.get("stattype")) {
            case "wins":
                lcolors.add("#1e88e5");
                lcolors.add("#e4002b");
                break;
            case "wins_percent":
                lcolors.add("#1e88e5");
                chartType = ChartTypes.STEPPEDAREACHART;
                break;
            case "kills":
                lcolors.add("#83D324");
                break;
            case "deaths":
                lcolors.add("#e4002b");
                break;
            case "kd":
                lcolors.add("#83D324");
                lcolors.add("#1e88e5");
                break;
            case "kda":
                lcolors.add("#83D324");
                lcolors.add("#1e88e5");
                lcolors.add("#e4002b");
                break;
            case "kills_per_match":
                lcolors.add("#D44527");
                chartType = ChartTypes.AREACHART;
                break;
            case "kills_per_minute":
                lcolors.add("#BB1D4A");
                chartType = ChartTypes.AREACHART;
                break;
            case "score_per_match":
                chartType = ChartTypes.AREACHART;
                break;
            case "score_per_minute":
                lcolors.add("#9B1A75");
                chartType = ChartTypes.AREACHART;
                break;
        }

        ArrayList<ArrayList<String>> d = (ArrayList<ArrayList<String>>) extras.get("lastmonth");
        ArrayList<ArrayList<String>> ds = new ArrayList<>(d.subList(d.size()-8,d.size()-1));
        ds.add(0,d.get(0));
        HtmlChartMaker html = new HtmlChartMaker(d, ds,chartType, true, lcolors, App.darkMode ? "#000000" : "#ffffff", App.darkMode ? "#ffffff" : "#000000");

        chart.loadData(html.getWebPage(),"text/html", null);

        chart.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                //Toast.makeText(ChartActivity.this,"Swipe Left", Toast.LENGTH_SHORT).show();
                //chart.loadUrl("javascript:nextChart()");
                //chart.loadUrl("javascript:chart.draw(data[current], options)");
                chart.evaluateJavascript("javascript:nextChart();", null);
            }
            @Override
            public void onSwipeRight() {
                //Toast.makeText(ChartActivity.this,"Swipe Right", Toast.LENGTH_SHORT).show();
                chart.evaluateJavascript("javascript:previousChart();", null);
            }
        });
    }
}
