package com.application.lostandfound.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.lostandfound.Fragments.HomeFragment;
import com.application.lostandfound.Fragments.ShowLostAndFoundFragment;
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

        // By default, the HomeFragment should appear.
        // However, while I doubt this will be an issue, I'll perform the transaction here just in case.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                .setReorderingAllowed(true)
                .replace(R.id.coreFragmentContainer, HomeFragment.newInstance(), null)
                .commit();
    }
}