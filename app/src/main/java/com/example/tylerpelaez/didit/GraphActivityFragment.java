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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A placeholder fragment containing a simple view.
 */
public class GraphActivityFragment extends Fragment {

    private GraphListAdapter mGraphListAdapter; //Graph adapter which will actually handle back-end for ListView in fragment

    public GraphActivityFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mGraphListAdapter = new GraphListAdapter(getActivity(), new ArrayList<String>());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false); //Inflate fragment to match parent activity view

        Intent intent = getActivity().getIntent();
        Habit habit = (Habit) intent.getExtras().getSerializable("Habit"); //Receive habit from intent so that graph data can be compiled

        ListView listView = (ListView) rootView.findViewById(R.id.graphListView); //List view which will be embedded in this fragment

        listView.setAdapter(mGraphListAdapter);

        //Find the number of valid descriptors for which graphs can be built
        Set<Map.Entry<Date, ArrayList<Descriptor>>> entries = habit.log.entrySet();
        ArrayList<Descriptor> descriptors = entries.iterator().next().getValue();

        Iterator<ArrayList<Descriptor>> descIt = habit.log.values().iterator();
        ArrayList<Integer> validDescriptors = new ArrayList<Integer>();

        for (int i = 0; i < descriptors.size(); ++i) {
            if (descriptors.get(i).isNumber) {
                validDescriptors.add(i);
            }
        }

        Log.d("debug","validDescriptors: " + validDescriptors.size());

        //Gather the lables (graph titles) of each descriptor which will be graphed
        ArrayList<String> labelsToAdd = new ArrayList<String>();
        for(int i : validDescriptors) {
            labelsToAdd.add(habit.labels.get(i));
        }

        Log.d("debug","label.get(0) is: " + labelsToAdd.get(0));

        //Gather all of the descriptor values needed in order to draw each graph
        ArrayList<ArrayList<Double>> descVals = new ArrayList<ArrayList<Double>>();

        for(int i: validDescriptors) {
            ArrayList<Double> toAdd = new ArrayList<Double>();
            Iterator<ArrayList<Descriptor>> it = habit.log.values().iterator();
            while (it.hasNext()) {
                ArrayList<Descriptor> current = it.next();
                toAdd.add(((Number)current.get(i)).getNum());
            }
            descVals.add(toAdd);
        }

        //Gather all of the dates into arraylists so that each graph can be drawn
        ArrayList<ArrayList<Date>> dateVals = new ArrayList<ArrayList<Date>>();

        for(int i: validDescriptors) {
            ArrayList<Date> toAdd = new ArrayList<Date>();
            Iterator<Date> it = habit.log.keySet().iterator();
            while(it.hasNext()) {
                Date current = it.next();
                toAdd.add(current);
            }
            dateVals.add(toAdd);
        }

        //Feed all of the data into the GraphListAdapter, which will actually construct the graphs
        if (mGraphListAdapter.getCount() == 0 && habit.log.size()>0) {
            //Log.d("debug","labelsToAdd.size() is " + labelsToAdd.size());
            //Log.d("debug","descVals.get(0).size is " + descVals.get(0).size());
            //Log.d("debug","dateVals.get(0).size() is " + dateVals.get(0).size());
            for(int i=0;i<labelsToAdd.size();++i) {
                //Log.d("debug","THIS IS " + dateVals.get(0).get(0));
                mGraphListAdapter.add(labelsToAdd.get(i),descVals.get(i),dateVals.get(i));
            }
        }

        return rootView;
    }
}
