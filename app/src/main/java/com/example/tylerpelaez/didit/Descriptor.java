package com.example.tylerpelaez.didit;

/**
 * Created by Stephen on 4/9/2017.
 */

public class Descriptor {
    String name;
    String value;

    public Descriptor(String n, String v){
        name = n;
        value = v;
    }

    String getName(){
        return name;
    }

    String getValue(){
        return value;
    }
}
