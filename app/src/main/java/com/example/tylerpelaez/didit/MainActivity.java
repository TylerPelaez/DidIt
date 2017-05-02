package com.example.tylerpelaez.didit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TaskViewFragment(), "TaskViewFragment")
                    .commit();
        }



        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

    }

    DialogListAdapter mDialogListAdapter;

    ArrayList<Descriptor> mDescriptors;

    public int mPosition;

    public void bringUpDialog(Habit habit, int position) {
        mDescriptors = new ArrayList<>();
        mPosition = position;
        //final ArrayList<Descriptor> myDescriptors = new ArrayList<>();

        LayoutInflater inflater = getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_complete, null);

        ListView dialogListView = (ListView) dialogLayout.findViewById(R.id.dialog_list_view);
        mDialogListAdapter = new DialogListAdapter(this, new ArrayList<String>());

        dialogListView.setAdapter(mDialogListAdapter);


        ArrayList<String> prompts = new ArrayList<>(habit.labels);
        ArrayList<String> types = new ArrayList<>(habit.descriptors);

        for (int i = 0; i < prompts.size(); ++i) {
            if (types.get(i).equals("Time")) {
                mDialogListAdapter.addTimePrompt(prompts.get(i));
            } else if (types.get(i).equals("Number")) {
                mDialogListAdapter.addNumberPrompt(prompts.get(i));
            } else if (types.get(i).equals("Text")) {
                mDialogListAdapter.addStringPrompt(prompts.get(i));
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Enter Information");

        builder.setView(dialogLayout);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK, so save the mSelectedItems results somewhere
                // or return them to the component that opened the dialog



                mDescriptors.addAll(mDialogListAdapter.entries);
                ((TaskViewFragment)(getSupportFragmentManager().findFragmentByTag("TaskViewFragment"))).updateHabitFromDialog();

                dialog.dismiss();

            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);



        //Log.d("TEXT", myDescriptors.toString());

        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);

    }
}
