package com.application.lostandfound.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.ViewModels.LostFoundViewModel;
import com.application.lostandfound.databinding.FragmentNewAdvertBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

/**
 * This fragment will be for users to fill in a form for a new lost/found item.
 */
public class NewAdvertFragment extends Fragment {

    // UI binding
    FragmentNewAdvertBinding binding;

    // Reference to our AutocompleteSupportFragment
    AutocompleteSupportFragment autocompleteSupportFragment;

    // Keep track of the the advert being for a lost or found item
    String itemState = "Lost";

    // Request code
    private int locationsRequestCode = 1;

    // Keep track of our location via the latitude and longitude
    private double latitude;
    private double longitude;

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
        binding = FragmentNewAdvertBinding.inflate(inflater, container, false);
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

        // Locations auto complete setup
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), PackageManager.GET_META_DATA);

            if (applicationInfo != null){

                String apiKey = applicationInfo.metaData.getString("com.google.android.geo.API_KEY");
                Places.initialize(getActivity().getApplicationContext(), apiKey);
            }


        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        autocompleteSupportFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.placesAutoCompleteFragment);
        autocompleteSupportFragment.setHint("Select Location");
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {

                Log.i("Error", "An error has occurred: " + status);
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {

                // Assign the latitude and longitude
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;

                autocompleteSupportFragment.setHint(place.getAddress());

            }
        });


        // Bind to the get current location button
        binding.getCurrentLocationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    // Deprecated but will just use it for now..
                    requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, locationsRequestCode);

                    return;
                }

                getCurrentLocation();
            }
        });

        // Set an on click listener when save is pressed
        // This will grab all inputs and the lost/found state and create the respective data class to be saved (for this task, it will be in a local database)
        binding.saveNewAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a new lost item
                // TODO If I ever improve on this task, I think better handling of inputs and validation needs to be done.
                LostFoundDataModel newLostItem = new LostFoundDataModel(
                        itemState,
                        binding.nameInputEditTextView.getText().toString(),
                        binding.phoneInputEditTextView.getText().toString(),
                        binding.descriptionInputEditTextView.getText().toString(),
                        binding.dateInputEditTextView.getText().toString(),
                        latitude,
                        longitude
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

    /**
     * Just a quick note, I am aware that this is deprecated.
     * In one of my non-task related projects, I have the other version of this but decided to just use this to make it quick.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == locationsRequestCode){

            getCurrentLocation();
        }


    }

    /**
     * Get the current location.
     * Just a note, even though the permissions is suppressed, this method will only get called if permissions were granted.
     */
    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        // If we manage to get a location, we can then store it in our variables to add in the database later
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        // Also, lets populate the location text box with said values.
                        // Just a note, I am only using the latitude and longitude as text here and not the actual address.
                        // Getting the address will require some extra logic using the GeoCoding API which I don't want to spent too much time for if not actually required.
                        autocompleteSupportFragment.setHint("Latitude: " + String.valueOf(latitude) + ", Longitude: " + String.valueOf(longitude));
                    }
                });
    }
}