package com.example.statsfrommoba;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchPlayersActivityModel {

    SearchPlayersActivity searchPlayersActivity;
    ArrayList<PlayerProfileSearchData> arrayList = new ArrayList<>();
    PlayerInfoSearchResultListAdapter adapter;
    ListView listView;

    SearchPlayersActivityModel(SearchPlayersActivity searchPlayersActivity) {
        this.searchPlayersActivity = searchPlayersActivity;

        /*
            Toolbar setup
         */
        Toolbar myToolbar = (Toolbar) searchPlayersActivity.findViewById(R.id.top_toolbar);
        searchPlayersActivity.setSupportActionBar(myToolbar);
        searchPlayersActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.searchPlayersActivity.getWindow().setStatusBarColor(this.searchPlayersActivity.getResources().getColor(R.color.toolbarColor, this.searchPlayersActivity.getTheme()));

        /*
            ListView setup
         */
        listView = (ListView) searchPlayersActivity.findViewById(R.id.list_view);
        adapter = new PlayerInfoSearchResultListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(searchPlayersActivity, "" + ((StringPair) listView.getItemAtPosition(position)).value, Toast.LENGTH_SHORT).show();
                Log.d("SEARCH PLAYER ACTIVITY MODEL", ((PlayerProfileSearchData) listView.getItemAtPosition(position)).toString());
                //Intent playerProfileScreen = new Intent(searchPlayersActivity,PlayerProfileActivity.class);
                //playerProfileScreen.putExtra("player_name", ((PlayerProfileSearchData) listView.getItemAtPosition(position)).playerName);
                //playerProfileScreen.putExtra("player_rank", ((PlayerProfileSearchData) listView.getItemAtPosition(position)).rank);
                //searchPlayersActivity.startActivity(playerProfileScreen);
                AsyncTask.execute(() -> {
                    PlayerProfileData profileData = RESTConnector.getPlayerProfileData(((PlayerProfileSearchData) listView.getItemAtPosition(position)).playerName);
                    searchPlayersActivity.runOnUiThread(() -> {
                        Intent playerProfileScreen = new Intent(searchPlayersActivity,PlayerProfileActivity.class);
                        playerProfileScreen.putExtras(PlayerProfileActivityModel.getPlayerProfileBundle(profileData));
                        // playerProfileScreen.putExtra("player_name", playerName);
                        searchPlayersActivity.startActivity(playerProfileScreen);
                    });
                });
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
                AsyncTask.execute(() -> {
                    List<PlayerProfileSearchData> foundPlayers = RESTConnector.getPlayersProfileSearchDataByName(s);
                    searchPlayersActivity.runOnUiThread(() -> updateList(foundPlayers));
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AsyncTask.execute(() -> {
                    if(isInteger(newText)) {
                        List<PlayerProfileSearchData> foundPlayers = RESTConnector.getPlayersProfileSearchDataByRank(Integer.parseInt(newText));
                        searchPlayersActivity.runOnUiThread(() -> updateList(foundPlayers));
                    } else {
                        List<PlayerProfileSearchData> foundPlayers = RESTConnector.getPlayersProfileSearchDataByName(newText);
                        searchPlayersActivity.runOnUiThread(() -> updateList(foundPlayers));
                    }
                });
                //adapter.getFilter().filter(newText);
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

    private void updateList(List<PlayerProfileSearchData> players) {
        adapter = new PlayerInfoSearchResultListAdapter(players);
        listView.setAdapter(adapter);
    }

    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
