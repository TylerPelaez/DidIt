package com.example.tylerpelaez.didit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        mTaskListAdapter = new TaskListAdapter(getActivity(), new ArrayList<String>());
        listView.setAdapter(mTaskListAdapter);
        mTaskListAdapter.add("Words");

        return rootView;

    }




}
