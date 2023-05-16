package com.application.lostandfound.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data model
 * This wil represent entries in the table.
 * So, from my understanding, the way Room works is that each entity will represent a singular table.
 * Initially, I thought it be a good idea to use two tables, one for lost and one for found.
 * However, since the fields are exactly the same, I figured it be easier to just add in an item state field representing if the item is lost or found.
 * For simplicity, I've kept everything as string entries.
 */

@Entity(tableName = "lost_found_table")
public class LostFoundDataModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "item_state")
    public String itemState;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;

    public LostFoundDataModel(String itemState, String name, String phoneNumber, String description, String date,  double latitude, double longitude) {
        this.itemState = itemState;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
