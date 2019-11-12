package com.example.statsfrommoba;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
            profileStatData = new PlayerProfileStatData();
            Log.d("REST", "Error in response");
        }
        return profileStatData;
    }

    public PlayerProfileData getPlayerProfileData(final String playerName){
        /*
            HTTP REST
        */
        final PlayerProfileData[] profileData = new PlayerProfileData[1];
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL endPoint = new URL(App.getAppResources().getString(R.string.rest_server_host) + "stats/player" + playerName);
                    HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
                    //connection.setRequestProperty("name", "test");
                    final int responseCode =  connection.getResponseCode();
                    //connection.getResponseMessage()
                    if(responseCode == 200) {
                        Log.d("HTTP", "Success response");
                        final InputStream responseBody = connection.getInputStream();
                        final InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                        ByteSource byteSource = new ByteSource() {
                            @Override
                            public InputStream openStream() throws IOException {
                                return responseBody;
                            }
                        };
                        final String text = byteSource.asCharSource(Charsets.UTF_8).read();
                        ObjectMapper objectMapper = new ObjectMapper();
                        profileData[0] = objectMapper.readValue(text,PlayerProfileData.class);

                        connection.disconnect();
                    } else {
                        profileData[0] = new PlayerProfileData();
                        Log.d("HTTP", "Error in response" + Integer.toString(responseCode));
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return profileData[0];
    }

    //private PlayerProfileStatData getPlayerProfileStat(){}
}
