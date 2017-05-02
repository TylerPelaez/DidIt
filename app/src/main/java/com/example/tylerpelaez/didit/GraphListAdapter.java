package com.example.tylerpelaez.didit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by M36 Jackson on 5/1/2017.
 */

public class GraphListAdapter extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> labels;
    public ArrayList<ArrayList<Double>> yaxis = new ArrayList<ArrayList<Double>>();
    public ArrayList<ArrayList<Date>> dates = new ArrayList<ArrayList<Date>>();

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
            Log.d("debug","Date: " + dates.get(position).get(i).toString());
            Log.d("debug","Yaxis: " + yaxis.get(position).get(i));
            points[i] = new DataPoint(dates.get(position).get(i),yaxis.get(position).get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setDrawDataPoints(true);
        graph.addSeries(series);

//        Iterator<DataPoint> iter = series.getValues(0, series.va);
//        for (; iter.hasNext();) {
//            DataPoint me = iter.next();
//            Log.d("DATA POINT:" , me.toString());
//        }


        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space

        // set manual x bounds to have nice steps

        int min = 7;
        if (dates.get(position).size() - 1 < min ) {
            min = dates.get(position).size() - 1;
        }
        graph.getViewport().setMinX(dates.get(position).get(0).getTime());
        graph.getViewport().setMaxX(dates.get(position).get(min).getTime());
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        graph.getGridLabelRenderer().setPadding(40);
        graph.getGridLabelRenderer().setHumanRounding(false);



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
