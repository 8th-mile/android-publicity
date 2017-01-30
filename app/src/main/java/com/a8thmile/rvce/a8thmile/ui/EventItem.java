package com.a8thmile.rvce.a8thmile.ui;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by vignesh on 19/1/17.
 */

public class EventItem implements Parcelable{
    private int backColId;
    private int imageId;
    private String title;
    public EventItem(int backColId, int imageId,String title)
    {
        this.backColId=backColId;
        this.imageId=imageId;
        this.title=title;
    }

    protected EventItem(Parcel in) {
        backColId = in.readInt();
        imageId = in.readInt();
        title = in.readString();
    }

    public static final Creator<EventItem> CREATOR = new Creator<EventItem>() {
        @Override
        public EventItem createFromParcel(Parcel in) {
            return new EventItem(in);
        }

        @Override
        public EventItem[] newArray(int size) {
            return new EventItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
dest.writeInt(backColId);
        dest.writeInt(imageId);

        dest.writeString(title);
    }
}
