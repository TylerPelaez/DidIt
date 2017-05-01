package com.example.tylerpelaez.didit;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Stephen on 4/10/2017.
 */

public class Number extends Descriptor implements Serializable {
    double num;
    //String label;

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeDouble(num);
//        out.writeString(label);
//    }
//
//    public static final Parcelable.Creator<Number> CREATOR = new Parcelable.Creator<Number>() {
//        public Number createFromParcel(Parcel in) {
//            return new Number(in);
//        }
//
//        public Number[] newArray(int size) {
//            return new Number[size];
//        }
//    };
//
//    private Number(Parcel in) {
//        num = in.readDouble();
//        label = in.readString();
//    }

    public Number(double n, String l){
        num = n;
        //label = l;
    }

    double getNum(){
        return num;
    }

    //String getLabel(){
    //    return label;
    //}
}
