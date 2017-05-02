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
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by M36 Jackson on 5/1/2017.
 */

public class GraphListAdapter extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> labels;
    public ArrayList<ArrayList<Double>> yaxis;
    public ArrayList<ArrayList<Date>> dates;

    public GraphListAdapter(Context context, ArrayList<String> labels) {
        super(context, R.layout.list_item_graph, labels);

        this.context = context;
        this.labels = labels;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_graph, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.list_item_graphtext);

        textView.setText(labels.get(position));

        GraphView graph = (GraphView) convertView.findViewById(R.id.graph);

        DataPoint[] points = new DataPoint[yaxis.get(position).size()];

        for(int i=0;i<yaxis.get(position).size();++i) {
            points[i] = new DataPoint(dates.get(position).get(i),yaxis.get(position).get(i));
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);

        graph.addSeries(series);

        return convertView;

    }

    public void add(String label, ArrayList<Double> yvals, ArrayList<Date> date) {
        labels.add(label);
        yaxis.add(yvals);
        dates.add(date);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        super.clear();
        labels.clear();
        yaxis.clear();
        dates.clear();
        notifyDataSetChanged();
    }
}
