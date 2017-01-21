package com.a8thmile.rvce.a8thmile.ui;

/**
 * Created by vignesh on 19/1/17.
 */

public class RowItem {
    private int imageId;
    private String descText;
    private String time;

    public RowItem(int imageId,String descText,String time)
    {
        this.imageId=imageId;
        this.descText=descText;
        this.time=time;
    }
    public int getImageId() {
        return imageId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescText() {

        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;

    }
}
