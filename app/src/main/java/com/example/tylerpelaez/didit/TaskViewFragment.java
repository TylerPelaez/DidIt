package com.example.tylerpelaez.didit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


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





        setHasOptionsMenu(true);



    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main_fragment, menu);


        return;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.task_view_fragment, container, false);


        ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        mTaskListAdapter = new TaskListAdapter(getActivity(), new ArrayList<Habit>());
        listView.setAdapter(mTaskListAdapter);
        mTaskListAdapter.add(new Habit("Test"));




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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_habit:
                showDialog();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);

        }
    }





    public void showDialog() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        CustomDialogFragment dialog = CustomDialogFragment.newInstance();
        dialog.show(getActivity().getFragmentManager(),"dialog fragment");

        //getActivity().getFragmentManager().beginTransaction().add(android.R.id.content, dialog).commit();
    }




}
