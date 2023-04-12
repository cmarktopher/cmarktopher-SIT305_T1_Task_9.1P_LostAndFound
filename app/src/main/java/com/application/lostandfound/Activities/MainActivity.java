package com.application.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.application.lostandfound.R;

/**
 * Some notes for the app
 * Based on https://developer.android.com/training/data-storage/sqlite,
 * it seems that a recommended approach is to use the Room Persistence Library.
 * As such, I am following this as well: https://developer.android.com/training/data-storage/room
 * A lot of what I am doing in terms of of architecture is based on this:
 * https://developer.android.com/codelabs/android-room-with-a-view#8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Will be using a fragment to add in the home/entry page.
        // This should appear by default without me adding anything here.
    }
}