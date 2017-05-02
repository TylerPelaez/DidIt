package com.example.tylerpelaez.didit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tylerpelaez on 4/6/17.
 */

public class TaskListAdapter extends ArrayAdapter<Habit> {

    private Context context;
    private LayoutInflater inflater;

    public ArrayList<Habit> habitList;

    public TaskListAdapter(Context context, ArrayList<Habit> habitList) {
        super(context, R.layout.list_item_task, habitList);

        this.context = context;
        this.habitList = habitList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_task, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_item_textview);

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_item_checkbox);

        textView.setText(habitList.get(position).name);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                RelativeLayout container = (RelativeLayout) buttonView.getParent();

                if (isChecked) {
                    container.setBackgroundColor(0xFF00FF00);
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).bringUpDialog(habitList.get(position));
                    }

                } else {
                    container.setBackgroundColor(0);
                }

            }
        });


        return convertView;

    }

    @Override
    public void clear() {
        super.clear();
        habitList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void add(Habit habit) {
        habitList.add(habit);
        notifyDataSetChanged();
    }


    @Nullable
    @Override
    public Habit getItem(int position) {
        if(position > habitList.size()) {
            return null;
        }
        return habitList.get(position);
    }

}
