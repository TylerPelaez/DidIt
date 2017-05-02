package com.example.tylerpelaez.didit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

//import android.support.v7.widget.RecyclerView.ViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tylerpelaez on 4/30/17.
 */

public class CreateHabitListAdapter extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater inflater;

    private int tmp_position;


    private ArrayList<String> strings;

    public ArrayList<String> labels;
    public ArrayList<String> types;

    public CreateHabitListAdapter(Context context, ArrayList<String> stringList) {
        super(context, R.layout.list_item_create_habit, stringList);

        this.context = context;
        this.strings = stringList;
        labels = new ArrayList<String>();
        types = new ArrayList<String>();

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_create_habit, parent, false);
        }



            EditText editText = (EditText) convertView.findViewById(R.id.list_item_edit_text);
            //tprompt1 = (TextView) convertView.findViewById(R.id.list_item_prompt);
            //holder.prompt2 = (TextView) convertView.findViewById(R.id.list_item_prompt2);
            Spinner typeSpinner = (Spinner) convertView.findViewById(R.id.list_item_spinner);


            //convertView.setTag(holder.editText);

            //Log.d("HOLDERDEF", Integer.toString(holder.ref));


            //Spinner spinner = (Spinner) convertView.findViewById(R.id.list_item_spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.descriptor_type_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            typeSpinner.setAdapter(adapter);


            int init = adapter.getPosition(types.get(position));
            typeSpinner.setSelection(init);

            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                private boolean initializedView = false;

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int myPosition, long id) {
                    if(!initializedView) {
                        initializedView = true;
                        return;
                    }
                    switch (parent.getId()) {
                        case R.id.list_item_spinner: {
                            String[] typeArray = getContext().getResources().getStringArray(R.array.descriptor_type_array);
                            types.set(position, typeArray[myPosition]);
                            break;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        editText.setText(labels.get(position));


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    EditText et =(EditText)v.findViewById(R.id.list_item_edit_text);
                    labels.set(position,
                            et.getText().toString().trim());
                    for(int i = 0; i < labels.size(); ++i) {
                        Log.d("yes", labels.get(i));
                    }
                }
            }
        });

        /*Log.d("TEST:", labels.get(position));
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //setting data to array, when changed


                for (int i = 0; i < labels.size(); ++i) {
                    Log.d(Integer.toString(i), labels.get(i));
                }
                labels.set(position,s.toString());
                Log.d(Integer.toString(position), "got here");
                for (int i = 0; i < labels.size(); ++i) {
                    Log.d(Integer.toString(i), labels.get(i));
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        return convertView;

    }

    @Override
    public void clear() {
        //super.clear();
        strings.clear();
        labels.clear();
        types.clear();
        notifyDataSetChanged();
    }

    @Override
    public void add(String string) {
        strings.add(string);
        labels.add("");
        types.add("Number");

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
        //super.remove(toRemove);
        for (int i = 0; i < strings.size(); ++i) {
            if (strings.get(i).equals(toRemove)) {
                strings.remove(i);
                labels.remove(i);
                types.remove(i);
                notifyDataSetChanged();
                return;
            }
        }

    }
}
