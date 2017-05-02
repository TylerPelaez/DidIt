package com.example.tylerpelaez.didit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by M36 Jackson on 5/1/2017.
 */

public class GraphListAdapter extends ArrayAdapter<Habit> {
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<Habit> habitList;

    public GraphListAdapter(Context context, ArrayList<Habit> habitList) {
        super(context, R.layout.list_item_task, habitList);

        this.context = context;
        this.habitList = habitList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_task, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_item_textview);

        textView.setText(habitList.get(position).name);

        return convertView;

    }
}
