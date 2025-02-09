package com.example.statsfrommoba;

import android.util.Log;
import android.widget.Toast;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HtmlChartMaker {

    public static String COLUMNCHART = "ColumnChart";
    public static String AREACHART = "AreaChart";
    public static String STEPPEDAREACHART = "SteppedAreaChart";
    String content;
    public HtmlChartMaker(ArrayList<ArrayList<String>> lastMonthData, ArrayList<ArrayList<String>> lastWeekData,ChartTypes chartType, boolean isStacked, ArrayList<String> colors, String backgroundColor, String textColor) {
        content =
                "<html>\n" +
                        "<head>\n" +
                        "    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "\n" +
                        "var lastMonth = [\n" +
                        makeStringFromDataTableForJS(lastMonthData) +
                        "]\n" +
                        "\n" +
                        "var lastWeek = [\n" +
                        makeStringFromDataTableForJS(lastWeekData) +
                        "]\n" +
                        "\n" +
                        "google.load(\"visualization\", \"1\", {'packages':['corechart']});\n" +
                        "google.setOnLoadCallback(drawChart);\n" +
                        "" +
                        "var current = 0;\n" +
                        "var chart = null;\n" +
                        "var button = null;\n" +
                        "var optionsLeft = null;\n" +
                        "var optionsRight = null;\n" +
                        "var data =[];\n" +
                        "\n" +
                        "function nextDataTypeIndex() {\n" +
                        "    current = current == 1 ? 0 : current + 1\n" +
                        "}\n" +
                        "\n" +
                        "function previousDataTypeIndex() {\n" +
                        "    current = current == 0 ? 1 : current - 1\n" +
                        "}\n" +
                        "\n" +
                        "function drawChart() {\n" +
                        "data[0] = google.visualization.arrayToDataTable(lastWeek);\n" +
                        "data[1] = google.visualization.arrayToDataTable(lastMonth);\n" +
                        "\n" +
                        "optionsLeft = {\n" +
                        "    legend: { position: 'top', maxLines: 9, textStyle: { color: '" + textColor + "' } },\n" +
                        "    bar: { groupWidth: '45%' },\n" +
                        "    series: {\n" +
                        "    " + makeStringFromColorsArray(colors) +
                        "    },\n" +
                        "    isStacked: " + (isStacked ? "true," : "false,") + "\n" +
                        "    animation: {\n" +
                        "        duration: 150,\n" +
                        "        easing: 'out',\n" +
                        "        startup: true,\n" +
                        "    },\n" +
                        "    chartArea: {\n" +
                        "        width: '84%',\n" +
                        "        height: '88%',\n" +
                        "    },\n" +
                        "    backgroundColor: '" + backgroundColor + "'," +
                        "    hAxis: { textStyle: { color: '" + textColor + "' }}," +
                        "    vAxis: { textStyle: { color: '" + textColor + "' }}," +
                        "};\n" +
                        "\n" +
                        "optionsRight = {\n" +
                        "    legend: { position: 'top', maxLines: 8, textStyle: { color: '" + textColor + "' } },\n" +
                        "    bar: { groupWidth: '45%' },\n" +
                        "    series: {\n" +
                        "    " + makeStringFromColorsArray(colors) +
                        "    },\n" +
                        "    isStacked: " + (isStacked ? "true," : "false,") + "\n" +
                        "    animation: {\n" +
                        "        duration: 150,\n" +
                        "        easing: 'out',\n" +
                        "        startup: true,\n" +
                        "    },\n" +
                        "    chartArea: {\n" +
                        "        width: '85%',\n" +
                        "        height: '88%',\n" +
                        "    },\n" +
                        "    backgroundColor: '" + backgroundColor + "'," +
                        "    hAxis: { textStyle: { color: '" + textColor + "' }}," +
                        "    vAxis: { textStyle: { color: '" + textColor + "' }}," +
                        "};\n" +
                        "\n" +
                        "chart = new google.visualization." + chartType.toString() +"(document.getElementById('chart_div'));\n" +
                        "button = document.getElementById('b1');\n" +
                        "\n" +
                        "chart.draw(data[current], optionsLeft);\n" +
                        "\n" +
                        "button.onclick = () => {\n" +
                        "    nextChart();\n" +
                        "};\n" +
                        "}" +
                        "function nextChart() {\n" +
                        "    console.log(`Current value: ${current}`);\n" +
                        "    nextDataTypeIndex();\n" +
                        "    console.log(`Current value after change: ${current}`);\n" +
                        "    \n" +
                        "    chart.draw(data[current], optionsLeft);\n" +
                        "}\n" +
                        "\n" +
                        "function previousChart() {\n" +
                        "    console.log(`Current value: ${current}`);\n" +
                        "    previousDataTypeIndex();\n" +
                        "    console.log(`Current value after change: ${current}`);\n" +
                        "    \n" +
                        "    chart.draw(data[current], optionsRight);\n" +
                        "}\n" +
                        "    </script>\n" +
                        "</head>\n" +
                        "<body style=\"background-color: " + backgroundColor + ";\">\n" +
                        "  <div id=\"chart_div\" style=\"width: 105%; height: 100%;\"></div>\n" +
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

    public static String makeStringFromColorsArray(String[] colors) {
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < colors.length; i++) {
            text.append(i);
            text.append(":{color: '");
            text.append(colors[i]);
            text.append("'},\n");
        }
        return text.toString();
    }

    public static String makeStringFromColorsArray(ArrayList<String> colors) {
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < colors.size(); i++) {
            text.append(i);
            text.append(":{color: '");
            text.append(colors.get(i));
            text.append("'},\n");
        }
        return text.toString();
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

    public static String makeStringFromDataTableForJS(ArrayList<ArrayList<String>> data) {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < data.size(); j++) {
            ArrayList<String> elements = data.get(j);
            text.append("[");
            for (int i = 0; i < elements.size(); i++) {
                text.append(elements.get(i));
                if (i < elements.size() - 1) {
                    text.append(", ");
                }
            }
            text.append("]");
            if(j < data.size()-1) {
                text.append(", \n");
            }
        }
        return text.toString();
    }

    public String getWebPage() {
        return content;
    }
}

