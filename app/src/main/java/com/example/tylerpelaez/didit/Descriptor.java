package com.example.tylerpelaez.didit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephen on 4/9/2017.
 */

public class Descriptor implements Parcelable {
    String name;
    String value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(value);
        out.writeString(name);
    }

    public static final Parcelable.Creator<Descriptor> CREATOR = new Parcelable.Creator<Descriptor>() {
        public Descriptor createFromParcel(Parcel in) {
            return new Descriptor(in);
        }

        public Descriptor[] newArray(int size) {
            return new Descriptor[size];
        }
    };

    private Descriptor(Parcel in) {
        value = in.readString();
        name = in.readString();
    }

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
