package com.application.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

/**
 * Some notes for the app
 * Based on https://developer.android.com/training/data-storage/sqlite,
 * it seems that a recommended approach is to use the Room Persistence Library.
 * As such, I am following this as well: https://developer.android.com/training/data-storage/room
 */
public class MainActivity extends AppCompatActivity {

    // Cache the data access objects so I don't have to keep getting them from the database
    private FoundDataAccessObject foundDao;
    private LostDataAccessObject lostDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        foundDao = LostAndFoundDatabase.getDatabase(this).foundDao();
        lostDao = LostAndFoundDatabase.getDatabase(this).lostDao();

    }
}