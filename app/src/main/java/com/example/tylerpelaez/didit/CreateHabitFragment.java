package com.example.tylerpelaez.didit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by tylerpelaez on 4/30/17.
 */

public class CreateHabitFragment extends Fragment {

    public CreateHabitFragment() {

    }


    public CreateHabitListAdapter mCreateHabitListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_create_habit, menu);


        return;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_habit_fragment, container, false);



        Spinner spinner = (Spinner) rootView.findViewById(R.id.number_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.number_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //int iCurrentSelection = spinner.getSelectedItemPosition();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            private boolean initializedView = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (!initializedView) {
                    initializedView = true;
                } else {

                    switch (parent.getId()) {
                        case R.id.list_item_spinner: {

                            break;
                        }
                        case R.id.number_spinner: {

                            Log.d("HI", Integer.toString(pos));
                            Log.d("HI", (String) parent.getSelectedItem());
                            for (int i = 15; i > pos - 1; i--) {
                                if (mCreateHabitListAdapter.getItem(i) != null) {

                                    mCreateHabitListAdapter.remove(Integer.toString(i));

                                }
                            }
                            for (int i = mCreateHabitListAdapter.getCount(); i < pos; i++) {
                                mCreateHabitListAdapter.add(Integer.toString(i));
                            }
                            Log.d("HERE", "yo");

                            break;
                        }
                    }

                }


                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }

        });


        Spinner day_spinner = (Spinner) rootView.findViewById(R.id.day_select_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.day_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        day_spinner.setAdapter(adapter2);

        //int iCurrentSelection = spinner.getSelectedItemPosition();

        day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            private boolean initializedView = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (!initializedView) {
                    initializedView = true;
                } else {

                    switch (parent.getId()) {
                        case R.id.day_select_spinner: {
                            if(pos == 0) {

                            } else if (pos == 1) {

                            }

                            break;
                        }
                    }

                }


                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }

        });



        ListView listView = (ListView) rootView.findViewById(R.id.create_listView);

        mCreateHabitListAdapter = new CreateHabitListAdapter(getContext(), new ArrayList<String>());

        listView.setAdapter(mCreateHabitListAdapter);

        return rootView;
    }










}
