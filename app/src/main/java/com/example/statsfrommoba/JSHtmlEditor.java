package com.example.statsfrommoba;

import android.util.Log;

import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSHtmlEditor {

    public static String COLUMNCHART = "ColumnChart";
    public static String AREACHART = "AreaChart";
    public static String STEPPEDAREACHART = "SteppedAreaChart";
    String content;
    String modifiedContent;
    public JSHtmlEditor(String pathToFile) {
        try {
            content = Files.asCharSource(new File("/android_asset/PrototypeChart.html"), StandardCharsets.UTF_8).read();
        } catch (java.io.IOException ex) {
            Log.d("JSHtmlEditor", ex.toString());
        }
    }

    public void setChartType(ChartTypes chartType) {
        content.replaceFirst("{chartType}", chartType.toString());
    }

    public void addDataString(String data) {
        String tag = "<data></data>";
        content.replaceFirst(tag, data);
        //content.indexOf(tag);
    }

    public static String makeStringFromDataTableForJS(List<List<String>> data) {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < data.size(); j++) {
            List<String> elements = data.get(j);
            text.append("[");
            for (int i = 0; i < elements.size(); i++) {
                text.append(elements.get(i));
                if (i < elements.size() - 1) {
                    text.append(", ");
                }
            }
            text.append("]");
            if(j < data.size()-1) {
                text.append(", ");
            }
        }
        return text.toString();
    }
}

