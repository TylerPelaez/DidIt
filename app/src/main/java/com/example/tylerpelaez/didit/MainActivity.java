package com.example.tylerpelaez.didit;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

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

    public ArrayList<Descriptor> bringUpDialog(Habit habit) {
        ArrayList<Descriptor> myDescriptors = new ArrayList<>();

        LayoutInflater inflater = getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_complete, null);

        ListView dialogListView = (ListView) dialogLayout.findViewById(R.id.dialog_list_view);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Enter Information");





        return myDescriptors;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


}
