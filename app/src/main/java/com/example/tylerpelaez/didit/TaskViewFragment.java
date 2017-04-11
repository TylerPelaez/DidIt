package com.example.tylerpelaez.didit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by tylerpelaez on 4/6/17.
 */

public class TaskViewFragment extends Fragment {

    private TaskListAdapter mTaskListAdapter;


    public TaskViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.task_view_fragment, container, false);


        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        mTaskListAdapter = new TaskListAdapter(getActivity(), new ArrayList<Habit>());
        listView.setAdapter(mTaskListAdapter);
        mTaskListAdapter.add(new Habit("Words"));

        //Button fab = (Button) rootView.findViewById(R.id.fab);
/*
        fab.setOnClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        } );
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent graphIntent = new Intent(getActivity(), GraphActivity.class)
                        .putExtra("Habit", mTaskListAdapter.getItem(position));
                startActivity(graphIntent);
            }
        });

        return rootView;

    }




}
