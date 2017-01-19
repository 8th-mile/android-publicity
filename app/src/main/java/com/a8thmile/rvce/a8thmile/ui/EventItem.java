package com.a8thmile.rvce.a8thmile.ui;

import android.widget.ImageView;

/**
 * Created by vignesh on 19/1/17.
 */

public class EventItem {
    private int backColId;
    private int imageId;
    private String title;
    public EventItem(int backColId, int imageId,String title)
    {
        this.backColId=backColId;
        this.imageId=imageId;
        this.title=title;
    }

    public int getBackColId() {
        return backColId;
    }

    public void setBackColId(int backColId) {
        this.backColId = backColId;
    }

    public int getIconId() {
        return imageId;
    }

    public void setIconId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
