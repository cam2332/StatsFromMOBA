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
    public JSHtmlEditor() {
        try {
            content = Files.asCharSource(new File("/PrototypeChart.html"), StandardCharsets.UTF_8).read();
            Log.d("HTML" ,content);
        } catch (java.io.IOException ex) {
            Log.d("JSHtmlEditor", ex.toString());
        }
    }

    public void setChartType(ChartTypes chartType) {
        content = content.replace("<chart_type></chart_type>", chartType.toString());
    }

    public void addDataString(String data) {
        String tag = "<data></data>";
        content = content.replace(tag, data);
        //content.indexOf(tag);
    }

    public static String makeStringFromDataTableForJS(String[][] data) {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < data.length; j++) {
            String[] elements = data[j];
            text.append("[");
            for (int i = 0; i < elements.length; i++) {
                text.append(elements[i]);
                if (i < elements.length - 1) {
                    text.append(", ");
                }
            }
            text.append("]");
            if(j < data.length-1) {
                text.append(", ");
            }
        }
        return text.toString();
    }

    public String getWebPage() {
        return content;
    }
}

