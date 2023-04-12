package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.ViewModels.LostFoundViewModel;
import com.application.lostandfound.databinding.FragmentNewAdvertBinding;

public class NewAdvertFragment extends Fragment {

    // Keep track of the the advert being for a lost or found item
    String itemState = "Lost";

    public NewAdvertFragment() {

        // Required empty public constructor
    }

    public static NewAdvertFragment newInstance() {
        NewAdvertFragment fragment = new NewAdvertFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create our binding and view
        FragmentNewAdvertBinding binding = FragmentNewAdvertBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Handle the lost toggle
        binding.lostRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemState = "Lost";
                binding.lostRadioButton.setChecked(true);
                binding.foundRadioButton.setChecked(false);
            }
        });

        // Handle the found toggle
        binding.foundRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemState = "Found";
                binding.lostRadioButton.setChecked(false);
                binding.foundRadioButton.setChecked(true);
            }
        });

        // Set an on click listener and grab all inputs and the lost/found state and create the respective class based on the toggle
        binding.saveNewAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a new lost item
                LostFoundDataModel newLostItem = new LostFoundDataModel(
                        itemState,
                        binding.nameInputEditTextView.getText().toString(),
                        binding.phoneInputEditTextView.getText().toString(),
                        binding.descriptionInputEditTextView.getText().toString(),
                        binding.dateInputEditTextView.getText().toString(),
                        binding.locationInputEditTextView.getText().toString()
                );

                // Now, we need to send this information to the database
                // We can grab the view model via the view model provider and make our insert via that
                LostFoundViewModel lostFoundViewModel = new ViewModelProvider(requireActivity()).get(LostFoundViewModel.class);
                lostFoundViewModel.insert(newLostItem);

                // I'll make it so that when you create a new advert, we go back to the home page
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        return view;
    }
}