package com.example.statsfrommoba;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchPlayersActivity extends AppCompatActivity {

    MyCustomListAdapter adapter;
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
        arrayList.add(new StringPair("WorstPlayer6","100"));
        arrayList.add(new StringPair("GoodPlayer7", "4"));
        arrayList.add(new StringPair("BadPlayer8", "30"));
        arrayList.add(new StringPair("GeniusPlayer9", "2"));
        arrayList.add(new StringPair("ProPlayer10", "5"));
        arrayList.add(new StringPair("NoobPlayer11", "15"));
        arrayList.add(new StringPair("RockPlayer12", "11"));

        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyCustomListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchPlayersActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setActivated(true);
        searchView.setQueryHint("Type player name or rank");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
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

