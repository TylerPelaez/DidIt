package com.example.tylerpelaez.didit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tylerpelaez on 4/30/17.
 */

public class CreateHabitFragment extends Fragment {

    public CreateHabitFragment() {

    }


    public CreateHabitListAdapter mCreateHabitListAdapter;
    private ArrayList<Integer> mSelectedItems;
    private boolean everyOther;
    private int num_skip;

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


        NDSpinner day_spinner = (NDSpinner) rootView.findViewById(R.id.day_select_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.day_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        day_spinner.setAdapter(adapter2);


        final TextView textView = (TextView) rootView.findViewById(R.id.text_view_days_selected);
        //int iCurrentSelection = spinner.getSelectedItemPosition();



        mSelectedItems = new ArrayList();
        num_skip = -1;
        everyOther = false;

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

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle(R.string.every_x_days_title)
                                        .setSingleChoiceItems(R.array.every_x_days_array, 0,
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        num_skip = which;
                                                    }
                                                })
                                        // Set the action buttons
                                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                                everyOther = true;
                                                // User clicked OK, so save the mSelectedItems results somewhere
                                                // or return them to the component that opened the dialog


                                                textView.setText(Integer.toString(num_skip + 1));
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.dismiss();
                                            }
                                        });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } else if (pos == 1) {

                                boolean[] itemsChecked = {false, false, false, false, false, false, false};

                                for(int i=0;i<itemsChecked.length;i++){
                                    if(mSelectedItems.contains(i))
                                        itemsChecked[i]=true;
                                }


                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle(R.string.pick_day_title)
                                        .setMultiChoiceItems(R.array.weekdays_array, itemsChecked,
                                                new DialogInterface.OnMultiChoiceClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which,
                                                                        boolean isChecked) {
                                                        if (isChecked) {
                                                            // If the user checked the item, add it to the selected items
                                                            mSelectedItems.add(which);
                                                        } else if (mSelectedItems.contains(which)) {
                                                            // Else, if the item is already in the array, remove it
                                                            mSelectedItems.remove(Integer.valueOf(which));
                                                        }
                                                    }
                                                })
                                        // Set the action buttons
                                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                everyOther = false;
                                                // User clicked OK, so save the mSelectedItems results somewhere
                                                // or return them to the component that opened the dialog
                                                String[] letters = getResources().getStringArray(R.array.weekdays_abbr_array);
                                                String newString = "";
                                                for (int i = 0; i < mSelectedItems.size(); ++i) {
                                                    newString += letters[mSelectedItems.get(i)];
                                                }

                                                textView.setText(newString);
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                mSelectedItems.clear();
                                                dialog.dismiss();
                                            }
                                        });

                                AlertDialog dialog = builder.create();
                                dialog.show();
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



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit_habit:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);

        }
    }











}
