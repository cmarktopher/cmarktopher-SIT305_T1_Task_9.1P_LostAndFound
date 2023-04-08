package com.application.lostandfound;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Data model
 * This wil represent entries in the table.
 * So, from my understanding, the way Room works is that each entity will represent a singular table.
 * Now, the issue here is we have a lost section and a found section.
 * So, I have a choice now of either:
 *      1. Introducing a variable to indicate if it is a lost or found entry...or....
 *      2. Have two separate tables which means I define two data models with the exact same properties.
 * I think option 2 is probably the best option.
 * Also, this will be good if I need to introduce unique variables to the respective data model.
 */

@Entity(tableName = "lost_table")
public class LostDataModel {

    @PrimaryKey(autoGenerate = true)
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
