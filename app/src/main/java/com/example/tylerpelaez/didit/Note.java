package com.example.tylerpelaez.didit;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Stephen on 4/10/2017.
 */

public class Note extends Descriptor implements Serializable {
    String note;

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeString(note);
//    }
//
//    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
//        public Note createFromParcel(Parcel in) {
//            return new Note(in);
//        }
//
//        public Note[] newArray(int size) {
//            return new Note[size];
//        }
//    };
//
//    private Note(Parcel in) {
//        note = in.readString();
//    }

    public Note(String n){
        note = n;
    }

    public void setNote(String n) {
        note = n;
    }

    public String getNote(){
        return note;
    }
}
