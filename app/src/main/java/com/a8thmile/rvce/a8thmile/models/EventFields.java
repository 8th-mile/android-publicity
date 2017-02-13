package com.a8thmile.rvce.a8thmile.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vignesh on 23/1/17.
 */

public class EventFields implements Parcelable{

    private String id;
    private String name;
    private String date;
    private int type;
    private int price;
    private String about;
    private String rules;
    private int first_prize;
    private int second_prize;

    protected EventFields(Parcel in) {
        id = in.readString();
        name = in.readString();
        date = in.readString();
        type = in.readInt();
        price = in.readInt();
        about = in.readString();
        rules = in.readString();
        first_prize = in.readInt();
        second_prize = in.readInt();
    }

    public static final Creator<EventFields> CREATOR = new Creator<EventFields>() {
        @Override
        public EventFields createFromParcel(Parcel in) {
            return new EventFields(in);
        }

        @Override
        public EventFields[] newArray(int size) {
            return new EventFields[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventFields(String id, String name, String date, int type, int price, String about, String rules,
                       int first_prize, int second_prize) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.price = price;
        this.about = about;
        this.rules = rules;
        this.first_prize = first_prize;
        this.second_prize = second_prize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public int getFirst_prize() {
        return first_prize;
    }

    public void setFirst_prize(int first_prize) {
        this.first_prize = first_prize;
    }

    public int getSecond_prize() {
        return second_prize;
    }

    public void setSecond_prize(int second_prize) {
        this.second_prize = second_prize;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeInt(type);
        parcel.writeInt(price);
        parcel.writeString(about);
        parcel.writeString(rules);
        parcel.writeInt(first_prize);
        parcel.writeInt(second_prize);

    }
}
