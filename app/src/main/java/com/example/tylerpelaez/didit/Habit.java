package com.example.tylerpelaez.didit;


import java.util.ArrayList;
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
    Boolean completed;
    int numDescriptors;
    ArrayList<Descriptor> descriptors;

    public Habit(String n){
        name = n;
        completed = false;
        numDescriptors = 0;
        descriptors = new ArrayList<Descriptor>();
    }

    void addDescriptor(String n, String v){
        Descriptor temp = new Descriptor(n,v);
        descriptors.add(temp);
    }


}
