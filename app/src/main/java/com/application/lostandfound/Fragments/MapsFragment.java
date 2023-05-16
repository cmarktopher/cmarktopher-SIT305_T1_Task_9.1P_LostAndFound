package com.application.lostandfound.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.application.lostandfound.R;
import com.application.lostandfound.ViewModels.LostFoundViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Just a note since this has some differences compared to what was shown in the workshop.
 * I ended up just creating a google maps fragment which filled out a lot of boilerplate code.
 * Here, the main difference is we create a new instance and implementation of our callback,
 * instead of using "implements OnMapReadyCallback".
 */
public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            // Lets go into our database and grab the latitude and longitude of each item
            LostFoundViewModel lostFoundViewModel = new ViewModelProvider(requireActivity()).get(LostFoundViewModel.class);
            lostFoundViewModel.getAllLostAndFoundItems().observe(getViewLifecycleOwner(), items -> {

                for (int i = 0; i < items.size(); i++) {

                    // Create markers based on the latitude and longitude.
                    LatLng marker = new LatLng(items.get(i).latitude, items.get(i).longitude);
                    googleMap.addMarker(new MarkerOptions().position(marker));

                    // Lets move the camera to the first item.
                    // We could probably get the average of the positions and move accordingly, but just decided to keep things simple.
                    if (i == 0){
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                    }
                }
            });




        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {

            mapFragment.getMapAsync(callback);
        }
    }
}