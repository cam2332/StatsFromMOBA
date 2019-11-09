package com.example.statsfrommoba;

import android.util.Log;

import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSHtmlEditor {

    String content;
    public JSHtmlEditor(String pathToFile) {
        try {
            content = Files.asCharSource(new File("/android_asset/RankChart.html"), StandardCharsets.UTF_8).read();
        } catch (java.io.IOException ex) {
            Log.d("JSHtmlEditor", ex.toString());
        }
    }

    public String makeStringFromDataTableForJS(List<List<String>> data) {
        StringBuilder text = new StringBuilder();
        for(List<String> elements : data) {
            text.append("[");
            for(String elem : elements) {

            }
        }
        return text.toString();
    }
}
