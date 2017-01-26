package com.a8thmile.rvce.a8thmile.models;

/**
 * Created by vignesh on 24/1/17.
 */

public class EventWishListGetResponse {

    private String name;
    private String date;
    private int type;
    private int price;

    public EventWishListGetResponse(String name, String date, int type, int price) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.price = price;
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
}
