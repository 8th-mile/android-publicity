package com.a8thmile.rvce.a8thmile.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vignesh on 23/1/17.
 */

public class EventFields implements Parcelable{
    public EventFields() {

    }

    private String id;
    private String name;
    private String date;
    private int type;
    private String price;
    private String about;
    private String rules;
    private String first_prize;
    private String second_prize;
    private String coord1;
    private String coord2;
    private String cphone1;
    private String cphone2;
    private int imgid;
    protected EventFields(Parcel in) {
        id = in.readString();
        name = in.readString();
        date = in.readString();
        type = in.readInt();
        price = in.readString();
        about = in.readString();
        rules = in.readString();
        first_prize = in.readString();
        second_prize = in.readString();
        coord1=in.readString();
        coord2=in.readString();
        cphone1=in.readString();
        cphone2=in.readString();
        imgid=in.readInt();
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

    public EventFields(String id, String name, String date, int type, String price, String about, String rules,
                       String first_prize, String second_prize, String coord1, String coord2, String cphone1, String cphone2, int imgid) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.price = price;
        this.about = about;
        this.rules = rules;
        this.first_prize = first_prize;
        this.second_prize = second_prize;
        this.coord1 = coord1;
        this.coord2 = coord2;
        this.cphone1 = cphone1;
        this.cphone2 = cphone2;
        this.imgid = imgid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getFirst_prize() {
        return first_prize;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public void setFirst_prize(String first_prize) {
        this.first_prize = first_prize;
    }

    public String getSecond_prize() {
        return second_prize;
    }

    public void setSecond_prize(String second_prize) {
        this.second_prize = second_prize;
    }

    public String getCoord1() {
        return coord1;
    }

    public void setCoord1(String coord1) {
        this.coord1 = coord1;
    }

    public String getCoord2() {
        return coord2;
    }

    public void setCoord2(String coord2) {
        this.coord2 = coord2;
    }

    public String getCphone1() {
        return cphone1;
    }

    public void setCphone1(String cphone1) {
        this.cphone1 = cphone1;
    }

    public String getCphone2() {
        return cphone2;
    }

    public void setCphone2(String cphone2) {
        this.cphone2 = cphone2;
    }

    public static Creator<EventFields> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeInt(type);
        parcel.writeString(price);
        parcel.writeString(about);
        parcel.writeString(rules);
        parcel.writeString(first_prize);
        parcel.writeString(second_prize);
        parcel.writeString(coord1);
        parcel.writeString(coord2);
        parcel.writeString(cphone1);
        parcel.writeString(cphone2);
        parcel.writeInt(imgid);
    }
}
