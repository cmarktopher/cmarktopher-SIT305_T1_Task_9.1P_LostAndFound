package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.RecyclerViews.LostAndFoundAdapter;
import com.application.lostandfound.ViewModels.LostFoundViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment will represent the view for users to see all lost and found items.
 * This will be using a recycler view with pressable cards that will then go into their respective item information fragment.
 */
public class ShowLostAndFoundFragment extends Fragment {

    // UI elements
    RecyclerView itemLostAndFoundRecycler;
    Button showItemsFragmentBackButton;

    // List for our current lost items
    private List<LostFoundDataModel> currentLostItems = new ArrayList<LostFoundDataModel>();

    public ShowLostAndFoundFragment() {
        // Required empty public constructor
    }

    public static ShowLostAndFoundFragment newInstance() {

        ShowLostAndFoundFragment fragment = new ShowLostAndFoundFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_lost_and_found, container, false);

        // Get UI elements
        itemLostAndFoundRecycler = view.findViewById(R.id.itemRecyclerView);

        // Create our recycler adapter and set it to our recycler view
        LostAndFoundAdapter lostAndFoundAdapter = new LostAndFoundAdapter(requireActivity(), currentLostItems);
        itemLostAndFoundRecycler.setAdapter(lostAndFoundAdapter);
        itemLostAndFoundRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // We need to go into our ViewModel and bind to the respective LiveView list of our items which will come from the database.
        LostFoundViewModel lostFoundViewModel = new ViewModelProvider(requireActivity()).get(LostFoundViewModel.class);
        lostFoundViewModel.getAllLostAndFoundItems().observe(getViewLifecycleOwner(), items -> {

            // Now, this portion of the code can likely be improved.
            // I've kept it relatively simple for now but I feel as though this is doubling up on our data collections for the items.
            // If I wanted to spend more time on this, I would look into getting the adapter to directly respond and update when the LiveView has updated.
            // TODO Improve on data collections handling for the LostAndFound adapter.
            currentLostItems.clear();
            currentLostItems.addAll(items);
            lostAndFoundAdapter.notifyDataSetChanged();
        });

        return view;
    }
}