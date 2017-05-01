package com.example.tylerpelaez.didit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

//import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

/**
 * Created by tylerpelaez on 4/30/17.
 */

public class CreateHabitListAdapter extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;

    private ViewHolder holder;

    private int tmp_position;


    private ArrayList<String> strings;

    public CreateHabitListAdapter(Context context, ArrayList<String> stringList) {
        super(context, R.layout.list_item_create_habit, stringList);

        this.context = context;
        this.strings = stringList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        tmp_position = position;

        if (convertView == null) {

            holder = new ViewHolder();


            convertView = inflater.inflate(R.layout.list_item_create_habit, null);

            holder.editText = (EditText) convertView
                    .findViewById(R.id.list_item_edit_text);
            holder.prompt1 = (TextView) convertView.findViewById(R.id.list_item_prompt);
            holder.prompt2 = (TextView) convertView.findViewById(R.id.list_item_prompt2);
            holder.typeSpinner = (Spinner) convertView.findViewById(R.id.list_item_spinner);



            //Spinner spinner = (Spinner) convertView.findViewById(R.id.list_item_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.descriptor_type_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            holder.typeSpinner.setAdapter(adapter);


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }
        return convertView;

    }

    @Override
    public void clear() {
        super.clear();
        strings.clear();
        notifyDataSetChanged();
    }

    @Override
    public void add(String string) {
        strings.add(string);
        notifyDataSetChanged();
    }


    @Nullable
    @Override
    public String getItem(int position) {
        if(position >= strings.size()) {
            return null;
        }
        //Log.d("DEBUG:", Integer.toString(position) + "," + Integer.toString(strings.size()));
        return strings.get(position);
    }

    @Override
    public void remove(String toRemove) {
        super.remove(toRemove);
        for (int i = 0; i < strings.size(); ++i) {
            if (strings.get(i).equals(toRemove)) {
                strings.remove(i);
                notifyDataSetChanged();
                return;
            }
        }

    }
}

class ViewHolder {
    EditText editText;
    TextView prompt1;
    TextView prompt2;
    Spinner typeSpinner;
    int ref;
}
