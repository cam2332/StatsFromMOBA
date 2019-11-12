package com.example.statsfrommoba;

import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchPlayerActivityModel {

    SearchPlayersActivity searchPlayersActivity;
    ArrayList<StringPair> arrayList = new ArrayList<>();
    PlayerInfoSearchResultListAdapter adapter;

    SearchPlayerActivityModel(SearchPlayersActivity searchPlayersActivity) {
        this.searchPlayersActivity = searchPlayersActivity;

        /*
            Toolbar setup
         */
        Toolbar myToolbar = (Toolbar) searchPlayersActivity.findViewById(R.id.top_toolbar);
        searchPlayersActivity.setSupportActionBar(myToolbar);
        searchPlayersActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
            ListView setup
         */
        final ListView listView = (ListView) searchPlayersActivity.findViewById(R.id.list_view);
        adapter = new PlayerInfoSearchResultListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(searchPlayersActivity, "" + ((StringPair) listView.getItemAtPosition(position)).value, Toast.LENGTH_SHORT).show();

                Intent playerProfileScreen = new Intent(searchPlayersActivity,PlayerProfileActivity.class);
                playerProfileScreen.putExtra("player_name", ((StringPair) listView.getItemAtPosition(position)).key);
                playerProfileScreen.putExtra("player_rank", ((StringPair) listView.getItemAtPosition(position)).value);
                searchPlayersActivity.startActivity(playerProfileScreen);
            }
        });

        /*
            SearchView setup
         */
        SearchView searchView = (SearchView) searchPlayersActivity.findViewById(R.id.search_view);
        searchView.setActivated(true);
        searchView.setQueryHint(App.getAppResources().getString(R.string.search_view_query_hint));
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
}
