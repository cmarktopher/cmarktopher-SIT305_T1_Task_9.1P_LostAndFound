package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.application.lostandfound.R;
import com.application.lostandfound.RecyclerViews.LostAndFoundAdapter;

/**
 * This fragment represents the entry point to the application for the user.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {

        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get UI elements
        Button newAdvertButton = view.findViewById(R.id.newAdvertButton);
        Button showItemsButton = view.findViewById(R.id.showItemsButton);
        Button showOnMapButton = view.findViewById(R.id.showOnMapButton);

        // This will bring us to the NewAdvertFragment
        newAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                        .setReorderingAllowed(true)
                        .replace(R.id.coreFragmentContainer, NewAdvertFragment.newInstance(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // This will bring us to the ShowLostAndFoundFragment
        showItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                        .setReorderingAllowed(true)
                        .replace(R.id.coreFragmentContainer, ShowLostAndFoundFragment.newInstance(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                        .setReorderingAllowed(true)
                        .replace(R.id.coreFragmentContainer, new MapsFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}