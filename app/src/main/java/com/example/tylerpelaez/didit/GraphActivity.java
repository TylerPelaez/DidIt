package com.example.tylerpelaez.didit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Toolbar provides back button in upper left corner
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) { //If there isn't any saved instance, make a new GraphActivityFragment

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.graph_container, new GraphActivityFragment(), "GraphActivityFragment")
                    .commit();
        }
    }

}
