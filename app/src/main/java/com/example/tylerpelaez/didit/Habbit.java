package com.example.tylerpelaez.didit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 4/9/2017.
 */

/*
    Variables:
        Name of habbit
        Completed
 */

public class Habbit {

    String name;
    Boolean completed;
    int numDescriptors;
    ArrayList<Descriptor> descriptors;

    public Habbit(String n){
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
