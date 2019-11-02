package com.example.statsfrommoba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayerInfoSearchResultListAdapter adapter;
    ArrayList<StringPair> arrayList = new ArrayList<>();

    public static Connect con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);

        /*
            JDBC
         */
        con = new Connect();
        if (con.getConnection() != null){
            Toast.makeText(MainActivity.this, "Connnected to DB", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Not connnected to DB", Toast.LENGTH_LONG).show();
        }

        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(llm);

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
        arrayList.add(new StringPair("NoobPlayer11", "8"));

        Collections.sort(arrayList,StringPair.ValueComparator);

        final ListView listView = (ListView) findViewById(R.id.main_list_view);
        adapter = new PlayerInfoSearchResultListAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + ((StringPair) listView.getItemAtPosition(position)).value, Toast.LENGTH_SHORT).show();

                Intent playerProfileScreen = new Intent(MainActivity.this,PlayerProfileActivity.class);
                playerProfileScreen.putExtra("player_name", ((StringPair) listView.getItemAtPosition(position)).key);
                playerProfileScreen.putExtra("player_rank", ((StringPair) listView.getItemAtPosition(position)).value);
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
        if(id == R.id.action_search_name) {
            Toast.makeText(MainActivity.this, "Action Clicked", Toast.LENGTH_LONG).show();

            Intent searchScreen = new Intent(this,SearchPlayersActivity.class);
            startActivityForResult(searchScreen,0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
