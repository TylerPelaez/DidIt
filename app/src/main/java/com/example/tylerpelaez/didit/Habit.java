package com.example.tylerpelaez.didit;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by Stephen on 4/9/2017.
 */


public class Habit extends JSONObject implements Serializable {
    public String name;
    ArrayList<String> labels;
    ArrayList<String> descriptors;
    // Key is date, Value is descriptors
    SortedMap<Date, ArrayList<Descriptor>> log;
    public boolean everyOther;
    public int num_skips;
    public HashMap<String,Boolean> weekdays;
    private Date startDate;

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeString(name);
//        out.writeStringList(labels);
//        out.writeStringList(descriptors);
//    }
//
//    public static final Parcelable.Creator<Habit> CREATOR = new Parcelable.Creator<Habit>() {
//        public Habit createFromParcel(Parcel in) {
//            return new Habit(in);
//        }
//
//        public Habit[] newArray(int size) {
//            return new Habit[size];
//        }
//    };
//
//    private Habit(Parcel in) {
//        name = in.readString();
//        in.readStringList(labels);
//        in.readStringList(descriptors);
//    }

    public Habit(String n){
        name = n;
        labels = new ArrayList<String>();
        descriptors = new ArrayList<String>();
        log = new TreeMap<>();
        everyOther = false;
        num_skips = 0;
        weekdays = new HashMap<String,Boolean>();
        weekdays.put("Monday",false);
        weekdays.put("Tuesday",false);
        weekdays.put("Wednesday",false);
        weekdays.put("Thursday",false);
        weekdays.put("Friday",false);
        weekdays.put("Saturday",false);
        weekdays.put("Sunday",false);
        startDate = new Date();
    }

    public ArrayList<ArrayList<String>> getDescriptors(){
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(labels);
        desc.add(descriptors);
        return desc;
    }

    void addDescriptor(String l, String d){
        labels.add(l);
        descriptors.add(d);
    }

    void removeDescriptor(String l){
        int loc = labels.indexOf(l);
        labels.remove(loc);
        descriptors.remove(loc);
    }

    void goalCompleted(ArrayList<Descriptor> desc){
        Date today = new Date();
        log.put(today,desc);
    }

    void addWeekday(String d){
        if(weekdays.containsKey(d)) {
            weekdays.put(d, true);
        }
    }

    void setEveryOther(boolean v, int num_days) {
        everyOther = v;
        num_skips = num_days;
    }

    void removeWeekday(String d){
        if(weekdays.containsKey(d)) {
            weekdays.put(d, false);
        }
    }

    public void addLabel(String l) {
        labels.add(l);
    }

    public void setLog(TreeMap<Date,ArrayList<Descriptor>> l) {
        log = l;
    }

}
