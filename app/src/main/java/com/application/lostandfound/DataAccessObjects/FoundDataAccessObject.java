package com.application.lostandfound.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.application.lostandfound.Models.FoundDataModel;

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
