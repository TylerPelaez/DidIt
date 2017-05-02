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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * Created by tylerpelaez on 4/6/17.
 */

public class TaskViewFragment extends Fragment {

    private TaskListAdapter mTaskListAdapter;

    private String filename = "content";

    private int requestCodeSent = 55;


    public TaskViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mTaskListAdapter = new TaskListAdapter(getActivity(), new ArrayList<Habit>());

        setHasOptionsMenu(true);

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);




        FileInputStream fis;
        File file = new File(getContext().getFilesDir(), filename);
        try {

            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Habit> habits = (ArrayList<Habit>) ois.readObject();


            // Necessary for the adapter to be updated
            for (int i = 0; i < habits.size(); ++i) {
                mTaskListAdapter.add(habits.get(i));
            }


        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }


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


        listView.setAdapter(mTaskListAdapter);
        //if (mTaskListAdapter.getCount() == 0) {
            Habit habit = new Habit("Test");

            habit.addLabel("Sit-Ups");

            HashMap<Date,ArrayList<Descriptor>> toAdd = new HashMap<Date,ArrayList<Descriptor>>();
            ArrayList<Descriptor> desclist = new ArrayList<Descriptor>();
            desclist.add(new Number(5.0));
            toAdd.put(new GregorianCalendar(2017, Calendar.JANUARY, 5).getTime(),desclist);
            Log.d("debug","2017/1/5 is " + new GregorianCalendar(2017,1,5).toString());
            desclist = new ArrayList<Descriptor>();
            desclist.add(new Number(2.0));
            toAdd.put(new GregorianCalendar(2017, Calendar.JANUARY, 6).getTime(),desclist);
            desclist = new ArrayList<Descriptor>();
            desclist.add(new Number(10.0));
            toAdd.put(new GregorianCalendar(2017,Calendar.JANUARY,7).getTime(),desclist);
            desclist = new ArrayList<Descriptor>();
            desclist.add(new Number(15.0));
            toAdd.put(new GregorianCalendar(2017,Calendar.JANUARY,8).getTime(),desclist);
            habit.setLog(toAdd);

            mTaskListAdapter.add(habit);
       // }




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
                launchCreateHabitScreen();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == requestCodeSent) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Habit newHabit = (Habit) data.getExtras().getSerializable("returnHabit");
                mTaskListAdapter.add(newHabit);
            }
        }
    }



    private void launchCreateHabitScreen() {
        Intent createIntent = new Intent(getActivity(), CreateHabitActivity.class);
        startActivityForResult(createIntent, requestCodeSent);
    }

    @Override
    public void onPause() {
        super.onPause();


        File file = new File(getContext().getFilesDir(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Log.d("adapter:", Integer.toString(mTaskListAdapter.getCount()));
            oos.writeObject(mTaskListAdapter.habitList);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
    }





}
