package com.example.tylerpelaez.didit;

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
    Map<String, String> descriptors;

    public Habbit(String n){
        name = n;
        completed = false;
        numDescriptors = 0;
        descriptors = new HashMap<String, String>();
    }

    void addDescriptor(String n, String v){
        descriptors.add(n,v);
    }
}
