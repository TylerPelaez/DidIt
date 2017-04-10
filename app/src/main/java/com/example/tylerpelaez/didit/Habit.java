package com.example.tylerpelaez.didit;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 4/9/2017.
 */

/*
    Variables:
        Name of habit
        Completed
 */

public class Habit {

    String name;
    int numDescriptors;
    ArrayList<String> completed;
    ArrayList<Descriptor> descriptors;

    public Habit(String n){
        name = n;
        completed = new ArrayList<String>();
        numDescriptors = 0;
        descriptors = new ArrayList<Descriptor>();
    }

    void addDescriptor(String n, String v){
        Descriptor temp = new Descriptor(n,v);
        descriptors.add(temp);
    }

    void goalCompleted(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        String today = sdf.format(Calendar.getInstance().get(Calendar.DATE));
        completed.add(today);
    }


}
