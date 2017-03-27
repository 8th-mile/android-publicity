package com.a8thmile.rvce.a8thmile.ui;

/**
 * Created by vignesh on 19/3/17.
 */

public class ExtraDetails {
    private String coord1;
    private String coord2;
    private String cphone1;
    private String cphone2;
    private int imgid;

    public ExtraDetails(String coord1, String coord2, String cphone1, String cphone2, int imgid) {
        this.coord1 = coord1;
        this.coord2 = coord2;
        this.cphone1 = cphone1;
        this.cphone2 = cphone2;
        this.imgid = imgid;
    }

    public String getCoord1() {
        return coord1;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
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
}
