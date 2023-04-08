package com.application.lostandfound;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// This will hold all our SQL queries for the found table

@Dao
public interface FoundDataAccessObject {

    @Query("SELECT * FROM found_table")
    List<FoundDataModel> getAllFoundItems();

    @Insert
    void insertNewFoundItem(FoundDataModel newFoundItem);

    @Delete
    void deleteFoundItem(FoundDataModel foundItemToDelete);

}
