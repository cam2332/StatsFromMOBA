package com.example.statsfrommoba;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTConnector {

    public PlayerProfileData GetPlayerProfileData(final String playerName){
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
}
