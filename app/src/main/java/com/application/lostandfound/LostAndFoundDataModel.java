package com.application.lostandfound;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class LostAndFoundDataModel {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "location")
    public String location;
}
