package com.example.tylerpelaez.didit;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Stephen on 4/9/2017.
 */

public class Descriptor implements Serializable {
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//
//    }
//
//    public static final Parcelable.Creator<Descriptor> CREATOR = new Parcelable.Creator<Descriptor>() {
//        public Descriptor createFromParcel(Parcel in) {
//            return new Descriptor(in);
//        }
//
//        public Descriptor[] newArray(int size) {
//            return new Descriptor[size];
//        }
//    };
//
//    private Descriptor(Parcel in) {
//
//    }
    boolean isNumber = false;
    public Descriptor(){

    }

    public boolean isNum(){
        return isNumber;
    }

}
