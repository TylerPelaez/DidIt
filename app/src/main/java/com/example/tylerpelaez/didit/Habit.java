package com.example.tylerpelaez.didit;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


/**
 * Created by Stephen on 4/9/2017.
 */


public class Habit extends JSONObject implements Serializable {
    String name;
    ArrayList<String> labels;
    ArrayList<String> descriptors;
    // Key is date, Value is descriptors
    HashMap<String, ArrayList<Descriptor>> log;

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
        log = new HashMap<String, ArrayList<Descriptor>>();
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
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String today = sdf.format(Calendar.getInstance().get(Calendar.DATE));
        log.put(today,desc);
    }




}
