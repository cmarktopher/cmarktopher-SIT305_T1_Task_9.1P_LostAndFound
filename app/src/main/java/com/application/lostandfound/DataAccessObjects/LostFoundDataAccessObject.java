package com.application.lostandfound.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.application.lostandfound.Models.LostFoundDataModel;

import java.util.List;

// This will hold all our SQL queries for the lost table

@Dao
public interface LostFoundDataAccessObject {

    @Query("SELECT * FROM lost_found_table")
    LiveData<List<LostFoundDataModel>> getAllLostItems();

    @Query("SELECT * FROM lost_found_table WHERE name = :name")
    LostFoundDataModel getLostItemByName(String name);

    @Insert
    void insertNewLostItem(LostFoundDataModel newLostItem);

    @Query("DELETE FROM lost_found_table WHERE name == :name")
    void deleteLostItem(String name);
}
