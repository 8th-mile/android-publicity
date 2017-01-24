package com.a8thmile.rvce.a8thmile.ui.Adapters;

import java.util.ArrayList;
public class Team_ContactUs {
    //PROPERTIES OF A SINGLE TEAM
    public String Name;
    public String Image;
    public ArrayList<String> contacts=new ArrayList<String>();
    public Team_ContactUs(String Name)
    {
        this.Name=Name;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Name;
    }
}
