package com.example.tylerpelaez.didit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TaskViewFragment(), "TaskViewFragment")
                    .commit();
        }

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


}
