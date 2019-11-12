package com.example.statsfrommoba;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchPlayersActivity extends AppCompatActivity {

    PlayerInfoSearchResultListAdapter adapter;
    ArrayList<StringPair> arrayList = new ArrayList<>();
    SearchPlayerActivityModel activityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_players);

        activityModel = new SearchPlayerActivityModel(this);

        arrayList.add(new StringPair("Player1","3"));
        arrayList.add(new StringPair("Player2","7"));
        arrayList.add(new StringPair("Player3","9"));
        arrayList.add(new StringPair("UnknownPlayer4","6"));
        arrayList.add(new StringPair("BestPlayer5","1"));
        arrayList.add(new StringPair("WorstPlayer6","10"));
        arrayList.add(new StringPair("GoodPlayer7", "4"));
        arrayList.add(new StringPair("BadPlayer8", "8"));
        arrayList.add(new StringPair("GeniusPlayer9", "2"));
        arrayList.add(new StringPair("ProPlayer10", "5"));





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }






}

