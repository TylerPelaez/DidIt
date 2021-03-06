package com.example.tylerpelaez.didit;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Stephen on 4/10/2017.
 */

public class Time extends Descriptor implements Serializable {
    int hour, min, sec;
    //String label;

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeString(label);
//        out.writeInt(hour);
//        out.writeInt(min);
//        out.writeInt(sec);
//    }
//
//    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
//        public Time createFromParcel(Parcel in) {
//            return new Time(in);
//        }
//
//        public Time[] newArray(int size) {
//            return new Time[size];
//        }
//    };
//
//    private Time(Parcel in) {
//        label = in.readString();
//        hour = in.readInt();
//        min = in.readInt();
//        sec = in.readInt();
//    }

    public Time(int h, int m, int s) {
        //label = l;
        hour = h;
        min = m;
        sec = s;
    }

    public void setHour( int h) {
        hour = h;
    }

    public void setMin( int m) {
        min = m;
    }


    public void setSec( int s) {
        sec = s;
    }

    int getHour(){
        return hour;
    }

    int getMin(){
        return min;
    }

    int getSec(){
        return sec;
    }

    String getTime(){
        return hour+":"+min+":"+sec;
    }
}
