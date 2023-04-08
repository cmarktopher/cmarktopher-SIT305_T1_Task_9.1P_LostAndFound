package com.application.lostandfound;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

/**
 * See LostDataModel for reasoning as to why there is two data models.
 */

@Entity(tableName = "found_table")
public class FoundDataModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "location")
    public String location;

    public FoundDataModel(String name, String phoneNumber, String description, String date, String location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
        this.location = location;
    }
}
