package com.example.tylerpelaez.didit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class GraphActivityFragment extends Fragment {

    private GraphListAdapter mGraphListAdapter;

    public GraphActivityFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mGraphListAdapter = new GraphListAdapter(getActivity(), new ArrayList<String>());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        Intent intent = getActivity().getIntent();
        Habit habit = (Habit) intent.getExtras().getSerializable("Habit");

        ListView listView = (ListView) rootView.findViewById(R.id.graphListView);

        listView.setAdapter(mGraphListAdapter);

        //TODO: Implementation here

//        if (mGraphListAdapter.getCount() == 0) {
//            for(int i=0;i<habit.labels.size();++i) {
//                mGraphListAdapter.add(habit.labels, );
//            }
//        }

        return rootView;
    }
}
