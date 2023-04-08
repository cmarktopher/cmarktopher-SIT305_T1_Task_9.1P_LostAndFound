package com.application.lostandfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

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

    // Counter to keep track of unique name for testing
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        foundDao = LostAndFoundDatabase.getDatabase(this).foundDao();
        lostDao = LostAndFoundDatabase.getDatabase(this).lostDao();

        LinearLayout layout = findViewById(R.id.linearLayout2);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                counter++;

                LostAndFoundDatabase.getDatabase(view.getContext()).databaseWriteExecutor.execute(()->{

                    List<LostDataModel> lostDataModels = lostDao.getAllLostItems();

                    for (int i = 0; i < lostDataModels.size(); i++) {

                        Log.d("Test", lostDataModels.get(i).name);
                    }

                });



            }
        });


    }
}