package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.FoundDataModel;
import com.application.lostandfound.Models.LostDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.databinding.FragmentNewAdvertBinding;

/**
 * Just a note, since the inputs will be exactly the same between lost and found (for now at least)
 * I will just have the post type as a toggle and use conditional logic to choose which table to send the data to.
 */
public class NewAdvertFragment extends Fragment {

    // Enum to represent the state of the advert (lost or found)
    private enum LostAndFoundToggleState {
        LOST,
        FOUND
    }

    private LostAndFoundToggleState currentLostAndFoundToggleState = LostAndFoundToggleState.LOST;

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

        // Handle the toggles
        binding.lostRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentLostAndFoundToggleState = LostAndFoundToggleState.LOST;
                binding.lostRadioButton.setChecked(true);
                binding.foundRadioButton.setChecked(false);
            }
        });

        binding.foundRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentLostAndFoundToggleState = LostAndFoundToggleState.FOUND;
                binding.lostRadioButton.setChecked(false);
                binding.foundRadioButton.setChecked(true);
            }
        });

        // Set an on click listener and grab all inputs and create the respective class based on the toggle
        binding.saveNewAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (currentLostAndFoundToggleState){

                    // If the current state is lost, then insert into the lost_table
                    case LOST:

                        // Create a new lost data model instance
                        LostDataModel newLostItem = new LostDataModel(
                                binding.nameInputEditTextView.toString(),
                                binding.phoneInputEditTextView.toString(),
                                binding.descriptionInputEditTextView.toString(),
                                binding.dateInputEditTextView.toString(),
                                binding.locationInputEditTextView.toString()
                        );

                        // Use the WriteExecutor to execute a database command
                        LostAndFoundDatabase.databaseWriteExecutor.execute(()->{

                            LostAndFoundDatabase.getDatabase(getContext()).lostDao().insertNewLostItem(newLostItem);

                        });

                        break;

                    // If the current state is found, then insert into the found_table
                    case FOUND:

                        // Create a new lost data model instance
                        FoundDataModel foundDataModel = new FoundDataModel(
                                binding.nameInputEditTextView.toString(),
                                binding.phoneInputEditTextView.toString(),
                                binding.descriptionInputEditTextView.toString(),
                                binding.dateInputEditTextView.toString(),
                                binding.locationInputEditTextView.toString()
                        );

                        // Use the WriteExecutor to execute a database command
                        LostAndFoundDatabase.databaseWriteExecutor.execute(()->{

                            LostAndFoundDatabase.getDatabase(getContext()).foundDao().insertNewFoundItem(foundDataModel);

                        });


                        break;
                }

            }
        });
        return view;
    }
}