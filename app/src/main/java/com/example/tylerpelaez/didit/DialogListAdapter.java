package com.example.tylerpelaez.didit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by tylerpelaez on 4/6/17.
 */

public class DialogListAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater inflater;

    public ArrayList<String> promptList;

    private TreeSet<Integer> mNumberSet = new TreeSet();
    private TreeSet<Integer> mStringSet = new TreeSet();

    private static final int TYPE_TIME = 0;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_STRING = 2;
    private static final int TYPE_MAX_COUNT = TYPE_STRING + 1;

    public ArrayList<Descriptor> entries;

    public DialogListAdapter(Context context, ArrayList<String> prompts) {
        super(context, R.layout.list_item_time, prompts);

        this.context = context;
        this.promptList = prompts;
        this.entries = new ArrayList<>();

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        int type = getItemViewType(position);

        if(convertView == null) {
            switch(type) {
                case TYPE_TIME:
                    convertView = inflater.inflate(R.layout.list_item_time, parent, false);
                    TextView timeTextView = (TextView) convertView.findViewById(R.id.time_list_text_view);
                    timeTextView.setText(promptList.get(position));

                    EditText timeEditHour = (EditText) convertView.findViewById(R.id.dialog_list_edit_time_hours);
                    timeEditHour.setText(Integer.toString(((Time)(entries.get(position))).getHour()));
                    timeEditHour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                EditText et =(EditText)v.findViewById(R.id.dialog_list_edit_time_hours);
                                if (et.getText().toString().equals("")) {
                                    ((Time)(entries.get(position))).setHour(0);
                                    et.setText(Integer.toString(0));
                                } else {
                                    ((Time) (entries.get(position))).setHour(Integer.parseInt(et.getText().toString().trim()));
                                }
                            }
                        }
                    });

                    EditText timeEditMinute = (EditText) convertView.findViewById(R.id.dialog_list_edit_time_minutes);
                    timeEditMinute.setText(Integer.toString(((Time)(entries.get(position))).getMin()));
                    timeEditMinute.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                EditText et =(EditText)v.findViewById(R.id.dialog_list_edit_time_minutes);
                                if (et.getText().toString().equals("")) {
                                    ((Time)(entries.get(position))).setMin(0);
                                    et.setText(Integer.toString(0));
                                }  else if (Integer.parseInt(et.getText().toString()) >= 60) {
                                    ((Time)(entries.get(position))).setMin(59);
                                    et.setText(Integer.toString(59));
                                } else {
                                    ((Time) (entries.get(position))).setMin(Integer.parseInt(et.getText().toString().trim()));
                                }
                            }
                        }
                    });

                    EditText timeEditSeconds = (EditText) convertView.findViewById(R.id.dialog_list_edit_time_seconds);
                    timeEditSeconds.setText(Integer.toString(((Time)(entries.get(position))).getSec()));
                    timeEditSeconds.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                EditText et =(EditText)v.findViewById(R.id.dialog_list_edit_time_seconds);
                                if (et.getText().toString().equals("")) {
                                    ((Time)(entries.get(position))).setSec(0);
                                    et.setText(Integer.toString(0));
                                }  else if (Integer.parseInt(et.getText().toString()) >= 60) {
                                    ((Time)(entries.get(position))).setSec(59);
                                    et.setText(Integer.toString(59));
                                }else {
                                    ((Time) (entries.get(position))).setSec(Integer.parseInt(et.getText().toString().trim()));
                                }
                            }
                        }
                    });


                    break;
                case TYPE_NUMBER:
                    convertView = inflater.inflate(R.layout.list_item_number, parent, false);
                    TextView numTextView = (TextView) convertView.findViewById(R.id.number_list_text_view);
                    numTextView.setText(promptList.get(position));

                    EditText numEditText = (EditText) convertView.findViewById(R.id.dialog_list_edit_number);
                    numEditText.setText(Double.toString(((Number)(entries.get(position))).getNum()));
                    numEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                EditText et =(EditText)v.findViewById(R.id.dialog_list_edit_number);
                                if (et.getText().toString().equals("")) {
                                    ((Number)(entries.get(position))).setNum(0);
                                    et.setText(Integer.toString(0));
                                } else {
                                    ((Number)(entries.get(position))).setNum(Double.parseDouble(et.getText().toString().trim()));
                                }
                            }
                        }
                    });

                    break;
                case TYPE_STRING:
                    convertView = inflater.inflate(R.layout.list_item_string, parent, false);
                    TextView stringTextView = (TextView) convertView.findViewById(R.id.string_list_text_view);
                    stringTextView.setText(promptList.get(position));

                    EditText stringEditText = (EditText) convertView.findViewById(R.id.dialog_list_edit_string);
                    stringEditText.setText(((Note)(entries.get(position))).getNote());
                    stringEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                EditText et =(EditText)v.findViewById(R.id.dialog_list_edit_string);
                                ((Note)(entries.get(position))).setNote(et.getText().toString().trim());
                            }
                        }
                    });


                    break;
            }
        }

        return convertView;

    }

    @Override
    public void clear() {
        super.clear();
        promptList.clear();
        entries.clear();
        notifyDataSetChanged();
    }

    public void addTimePrompt(String timePrompt) {
        promptList.add(timePrompt);
        entries.add(new Time(0, 0, 0 ));
        notifyDataSetChanged();
    }

    public  void addNumberPrompt(String numberPrompt) {
        promptList.add(numberPrompt);
        mNumberSet.add(promptList.size() - 1);
        entries.add(new Number(0));
        notifyDataSetChanged();

    }

    public void addStringPrompt(String stringPrompt) {
        promptList.add(stringPrompt);
        mStringSet.add(promptList.size() - 1);
        entries.add(new Note(""));
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mStringSet.contains(position)) {
            return TYPE_STRING;
        } else if (mNumberSet.contains(position)) {
            return TYPE_NUMBER;
        } else {
            return TYPE_TIME;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return promptList.size();
    }


    @Override
    public String getItem(int position) {
        return promptList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
