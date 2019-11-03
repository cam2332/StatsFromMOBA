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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_players);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList.add(new StringPair("Player1","3"));
        arrayList.add(new StringPair("Player2","7"));
        arrayList.add(new StringPair("Player3","9"));
        arrayList.add(new StringPair("UnknownPlayer4","6"));
        arrayList.add(new StringPair("BestPlayer5","1"));
        arrayList.add(new StringPair("WorstPlayer6","12"));
        arrayList.add(new StringPair("GoodPlayer7", "4"));
        arrayList.add(new StringPair("BadPlayer8", "11"));
        arrayList.add(new StringPair("GeniusPlayer9", "2"));
        arrayList.add(new StringPair("ProPlayer10", "5"));
        arrayList.add(new StringPair("NoobPlayer11", "10"));
        arrayList.add(new StringPair("Player1","13"));
        arrayList.add(new StringPair("Player2","14"));
        arrayList.add(new StringPair("Player3","15"));
        arrayList.add(new StringPair("UnknownPlayer4","16"));
        arrayList.add(new StringPair("BestPlayer5","17"));
        arrayList.add(new StringPair("WorstPlayer6","18"));
        arrayList.add(new StringPair("GoodPlayer7", "19"));
        arrayList.add(new StringPair("BadPlayer8", "20"));
        arrayList.add(new StringPair("GeniusPlayer9", "21"));
        arrayList.add(new StringPair("ProPlayer10", "22"));
        arrayList.add(new StringPair("NoobPlayer11", "8"));

        final ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new PlayerInfoSearchResultListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchPlayersActivity.this, "" + ((StringPair) listView.getItemAtPosition(position)).value, Toast.LENGTH_SHORT).show();

                Intent playerProfileScreen = new Intent(SearchPlayersActivity.this,PlayerProfileActivity.class);
                playerProfileScreen.putExtra("player_name", ((StringPair) listView.getItemAtPosition(position)).key);
                playerProfileScreen.putExtra("player_rank", ((StringPair) listView.getItemAtPosition(position)).value);
                startActivity(playerProfileScreen);
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setActivated(true);
        searchView.setQueryHint("Type player name or rank");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        //searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("SearchView " , "Focus on searchview");
            }
        });

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

