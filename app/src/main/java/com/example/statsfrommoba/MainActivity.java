package com.example.statsfrommoba;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayerInfoSearchResultListAdapter adapter;
    ArrayList<StringPair> arrayList = new ArrayList<>();
    MainActivityModel activityModel;

    public static Connect con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityModel = new MainActivityModel(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);

        activityModel.addProfileStatSmall("Best Player", "1", "Player3");
        activityModel.addProfileStatSmall("Most Wins", "159", "Player1");
        activityModel.addProfileStatSmall("Most Kills", "457", "Player2");

        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(llm);


        /*
         SearchView
         */
        SearchView searchView = (SearchView) findViewById(R.id.main_search_view);
        searchView.setActivated(true);
        searchView.setQueryHint("Type player name or rank");
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        setSearchViewOnClickListener(searchView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_LONG).show();
                Log.d("searchView test", "Click");
                Intent searchScreen = new Intent(MainActivity.this,SearchPlayersActivity.class);
                startActivityForResult(searchScreen,0);
            }
        });

        arrayList.add(new StringPair("3","UnknownPlayer4"));
        arrayList.add(new StringPair("1","BestPlayer5"));
        arrayList.add(new StringPair("2", "GeniusPlayer9"));

        Collections.sort(arrayList,StringPair.KeyNumberComparator);
        //arrayList.add(0, new StringPair("Rank", "Player"));

        final ListView listView = (ListView) findViewById(R.id.main_list_view);
        adapter = new PlayerInfoSearchResultListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + ((StringPair) listView.getItemAtPosition(position)).value, Toast.LENGTH_SHORT).show();

                Intent playerProfileScreen = new Intent(MainActivity.this,PlayerProfileActivity.class);
                playerProfileScreen.putExtra("player_name", ((StringPair) listView.getItemAtPosition(position)).value);
                playerProfileScreen.putExtra("player_rank", ((StringPair) listView.getItemAtPosition(position)).key);
                startActivity(playerProfileScreen);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*
        if(id == R.id.action_search_name) {
            Toast.makeText(MainActivity.this, "Action Clicked", Toast.LENGTH_LONG).show();

            Intent searchScreen = new Intent(this,SearchPlayersActivity.class);
            startActivityForResult(searchScreen,0);
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    public static void setSearchViewOnClickListener(View v, View.OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)v;
            int count = group.getChildCount();
            for ( int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }
                if (child instanceof TextView) {
                    TextView text = (TextView)child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }
}
