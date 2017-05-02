package com.example.tylerpelaez.didit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        mGraphListAdapter = new GraphListAdapter(getActivity(), new ArrayList<Habit>());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Intent intent = getActivity().getIntent();
        Habit habit = (Habit) intent.getExtras().getSerializable("Habit");


        return inflater.inflate(R.layout.fragment_graph, container, false);
    }
}
