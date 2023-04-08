package com.application.lostandfound;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// This will hold all our SQL queries for the lost table

@Dao
public interface LostDataAccessObject {

    @Query("SELECT * FROM lost_table")
    List<LostDataModel> getAllLostItems();

    @Query("SELECT * FROM lost_table WHERE name = :name")
    LostDataModel getLostItemByName(String name);

    @Insert
    void insertNewLostItem(LostDataModel newLostItem);

    @Query("DELETE FROM lost_table WHERE name == :name")
    void deleteLostItem(String name);
}
