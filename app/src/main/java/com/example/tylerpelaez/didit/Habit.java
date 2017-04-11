package com.example.tylerpelaez.didit;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


/**
 * Created by Stephen on 4/9/2017.
 */


public class Habit implements Parcelable {
    String name;
    int numDescriptors;
    ArrayList<String> completed;
    ArrayList<Descriptor> descriptors;
    HashMap<String, ArrayList<Descriptor>> log;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(numDescriptors);
        out.writeString(name);
        out.writeStringList(completed);
        out.writeTypedList(descriptors);
    }

    public static final Parcelable.Creator<Habit> CREATOR = new Parcelable.Creator<Habit>() {
        public Habit createFromParcel(Parcel in) {
            return new Habit(in);
        }

        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };

    private Habit(Parcel in) {
        numDescriptors = in.readInt();
        name = in.readString();
        in.readStringList(completed);
        in.readTypedList(descriptors,Descriptor.CREATOR);
    }

    public Habit(String n){
        name = n;
        completed = new ArrayList<String>();
        numDescriptors = 0;
        descriptors = new ArrayList<Descriptor>();
        log = new HashMap<String, ArrayList<Descriptor>>();
    }

    void addTime(int h, int m, int s, String l){
        Descriptor temp = new Time(h,m,s,l);
        descriptors.add(temp);
        numDescriptors++;
    }

    void addNote(String n){
        Descriptor temp = new Note(n);
        descriptors.add(temp);
        numDescriptors++;
    }

    void addNumber(double n){
        Descriptor temp = new Number(n);
        descriptors.add(temp);
        numDescriptors++;
    }

    void goalCompleted(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String today = sdf.format(Calendar.getInstance().get(Calendar.DATE));
        completed.add(today);
    }


}
