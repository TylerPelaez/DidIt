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

public class TaskListAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> taskInfo;

    public TaskListAdapter(Context context, ArrayList<String> taskInfoString) {
        super(context, R.layout.list_item_task, taskInfoString);

        this.context = context;
        this.taskInfo = taskInfoString;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_task, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_item_textview);

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_item_checkbox);



        textView.setText(taskInfo.get(position));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                RelativeLayout container = (RelativeLayout) buttonView.getParent();

                container.setBackgroundColor(0xFF00FF00);

            }
        });


        return convertView;

    }

    @Override
    public void clear() {
        super.clear();
        taskInfo.clear();
        notifyDataSetChanged();
    }

    @Override
    public void add(String string) {
        taskInfo.add(string);
        notifyDataSetChanged();
    }


    @Nullable
    @Override
    public String getItem(int position) {
        if(position > taskInfo.size()) {
            return null;
        }
        return taskInfo.get(position);
    }

}
