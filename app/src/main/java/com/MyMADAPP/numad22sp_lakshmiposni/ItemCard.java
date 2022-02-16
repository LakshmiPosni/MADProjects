package com.MyMADAPP.numad22sp_lakshmiposni;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

public class ItemCard implements ItemClickListener {

    //private final int imageSource;
    private final String itemName;
    private final String itemUrl;
    private boolean isChecked;

    //Constructor
    public ItemCard(String itemName, String itemUrl, boolean isChecked) {
        //this.imageSource = imageSource;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.isChecked = isChecked;
    }

    //Getters for the imageSource, itemName and itemDesc
//    public int getImageSource() {
//        return imageSource;
//    }

    public String getUrl() {
        return itemUrl;
    }

    public String getItemName() {
        return itemName; //+ (isChecked ? "(checked)" : "");
    }

    public boolean getStatus() {
        return isChecked;
    }


    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }

    @Override
    public void onCheckBoxClick(int position) {
        isChecked = !isChecked;
    }

}



















