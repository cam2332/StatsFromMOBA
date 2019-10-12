package com.example.statsfrommoba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);

        //recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(llm);
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
