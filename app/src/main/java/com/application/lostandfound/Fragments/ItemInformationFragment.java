package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.ViewModels.LostFoundViewModel;
import com.application.lostandfound.databinding.FragmentItemDisplayBinding;
import com.application.lostandfound.databinding.FragmentNewAdvertBinding;

/**
 * Fragment showing all the information for a specific item
 * This will transition from a recycler view item.
 */
public class ItemInformationFragment extends Fragment {

    public ItemInformationFragment() {

        // Required empty public constructor
    }

    public static ItemInformationFragment newInstance() {

        ItemInformationFragment fragment = new ItemInformationFragment();
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
        FragmentItemDisplayBinding binding = FragmentItemDisplayBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Get our current set item from our view model
        LostFoundViewModel lostFoundViewModel = new ViewModelProvider(requireActivity()).get(LostFoundViewModel.class);
        LostFoundDataModel item = lostFoundViewModel.getCurrentItemToView();

        // Set the UI elements
        binding.itemDisplayFragmentNameView.setText(item.itemState + " " + item.name);
        binding.itemDisplayFragmentDate.setText("Date: " + item.date);
        binding.itemDisplayFragmentLocation.setText("Location: " + item.location);
        binding.itemDisplayFragmentPhone.setText("Contact: " + item.phoneNumber);
        binding.itemDisplayFragmentDescription.setText(item.description);

        // Bind to back button
        binding.itemDisplayBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();

            }
        });

        // Bind to the remove item button
        binding.itemDisplayRemoveItemButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Simply call the delete method from the lost and found view model - this will also auto update the live model list of items
                lostFoundViewModel.delete(item);

                // Return back to the previous fragment which will be our recycler view of items.
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}