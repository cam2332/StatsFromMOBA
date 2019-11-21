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
    public JSHtmlEditor(String[][] data, ChartTypes chartType) {
        content = "<html>\n" +
                "<head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "          google.load(\"visualization\", \"1\", {'packages':['corechart']});\n" +
                "          google.setOnLoadCallback(drawChart);\n" +
                "\n" +
                "          function drawChart() {\n" +
                "            var data = google.visualization.arrayToDataTable([\n" +
                "                " + makeStringFromDataTableForJS(data) + "\n" +
                "            ]);\n" +
                "\n" +
                "            var options = {\n" +
                "                legend: { position: 'top', maxLines: 3 },\n" +
                "                bar: { groupWidth: '30%' },\n" +
                "                series: {\n" +
                "                    0:{color: '#1e88e5'},\n" +
                "                    1:{color: '7bb241'},\n" +
                "                    2:{color: 'e53935'}\n" +
                "                },\n" +
                "                chartArea: {\n" +
                "                width: '92%',\n" +
                "                height: '90%',\n" +
                "                },\n" +
                "                width: '100%'\n" +
                "            };\n" +
                "\n" +
                "            var chart = new google.visualization." + chartType.toString() + "(document.getElementById('chart_div'));\n" +
                "            chart.draw(data, options);\n" +
                "          }\n" +
                "        </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"chart_div\" style=\"width: 100%; height: 100%;\"></div>\n" +
                "</body>\n" +
                "</html>";
        Log.d("HTML" ,content);
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
                text.append(", \n");
            }
        }
        return text.toString();
    }

    public String getWebPage() {
        return content;
    }
}

