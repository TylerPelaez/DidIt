package com.example.tylerpelaez.didit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by M36 Jackson on 5/1/2017.
 */

public class GraphListAdapter extends ArrayAdapter<Habit> {
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<Habit> habitList;

    public GraphListAdapter(Context context, ArrayList<Habit> habitList) {
        super(context, R.layout.list_item_graph, habitList);

        this.context = context;
        this.habitList = habitList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_graph, parent, false);
        }

//        TextView textView = (TextView) convertView.findViewById(R.id.list_item_graphtext);
//
//        textView.setText(R.string.hello_world);

        GraphView graph = (GraphView) convertView.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        return convertView;

    }

    @Override
    public void add(Habit habit) {
        habitList.add(habit);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        super.clear();
        habitList.clear();
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
