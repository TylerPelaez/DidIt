package com.example.tylerpelaez.didit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class GraphActivityFragment extends Fragment {

    public GraphActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Intent intent = getActivity().getIntent();
        Habit habit = (Habit) intent.getExtras().getSerializable("Habit");



        return inflater.inflate(R.layout.fragment_graph, container, false);
    }
}
