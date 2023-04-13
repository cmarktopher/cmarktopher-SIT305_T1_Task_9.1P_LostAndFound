package com.application.lostandfound.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.application.lostandfound.Models.LostFoundDataModel;

import java.util.List;

/**
 * This interface will hold all our SQL queries for the lost and found table.
 */

@Dao
public interface LostFoundDataAccessObject {

    @Query("SELECT * FROM lost_found_table")
    LiveData<List<LostFoundDataModel>> getAllLostItems();

    @Insert
    void insertNewLostItem(LostFoundDataModel newLostItem);

    @Query("DELETE FROM lost_found_table WHERE uid == :id")
    void deleteLostItem(Integer id);
}
