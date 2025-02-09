package com.example.statsfrommoba;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;

import org.javatuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class RESTConnector {

    private static String getResponseFromServer(final String urlEndPart){
        String response = "";
                try {
                    URL endPoint = new URL(App.getAppResources().getString(R.string.rest_server_host) + urlEndPart);
                    HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
                    int responseCode =  connection.getResponseCode();
                    if(responseCode == 200){
                        final InputStream responseBody = connection.getInputStream();
                        ByteSource byteSource = new ByteSource() {
                            @Override
                            public InputStream openStream() throws IOException {
                                return responseBody;
                            }
                        };
                        String text = byteSource.asCharSource(Charsets.UTF_8).read();
                        Log.d("REST Response code ", String.valueOf(responseCode));
                        Log.d("REST Response ", text);
                        response = text;
                        Log.d("REST TEST ", String.valueOf(responseCode) + " _ " + text);
                        connection.disconnect();
                    }else{
                        response = "Error";
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        return response;
    }

    public static PlayerProfileStatData getPlayerProfileStatData(final String endUrl) {
        PlayerProfileStatData profileStatData = new PlayerProfileStatData();

        String response = getResponseFromServer(endUrl);
        if(response != null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                profileStatData = objectMapper.readValue(response,PlayerProfileStatData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.d("REST", "Error in response");
        }
        return profileStatData;
    }

    public static List<PlayerProfileSearchData> getPlayersProfileSearchDataByName(final String playerName) {
        List<PlayerProfileSearchData> profileSearchData = Arrays.asList();

        String response = getResponseFromServer("stats/player/search/" + playerName);
        if(response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                profileSearchData = Arrays.asList(objectMapper.readValue(response,PlayerProfileSearchData[].class));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("REST", "Error in getPlayerProfileSearchDataByName");
        }
        return profileSearchData;
    }

    public static List<PlayerProfileSearchData> getPlayersProfileSearchDataByRank(final Integer playerRank) {
        List<PlayerProfileSearchData> profileSearchData = Arrays.asList();

        String response = getResponseFromServer("stats/player/search/" + playerRank);
        if(response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                profileSearchData = Arrays.asList(objectMapper.readValue(response,PlayerProfileSearchData[].class));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("REST", "Error in getPlayerProfileSearchDataByRank");
        }
        return profileSearchData;
    }


    public static JsonNode getPlayerProfileHistoryDate(final String playerName, String statType) {
        JsonNode node = null;

        String response = getResponseFromServer("stats/player/history/" + playerName + "/" + statType);
        if(response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                node = objectMapper.readTree(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("REST", "Error in getPlayerProfileHistoryData");
        }
        return node;
    }


    public static PlayerProfileData getPlayerProfileData(final String playerName) {
        PlayerProfileData profileData = new PlayerProfileData();

        String response = getResponseFromServer("stats/player/profile/" + playerName);
        System.out.println("RESPONSE ___________________________________ " + response);
        if(response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                profileData = objectMapper.readValue(response,PlayerProfileData.class);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("REST", "Error in getPlayerProfileData");
        }
        return profileData;
    }
}
