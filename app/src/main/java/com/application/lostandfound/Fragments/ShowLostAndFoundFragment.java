package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.RecyclerViews.LostAndFoundAdapter;
import com.application.lostandfound.ViewModels.LostFoundViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShowLostAndFoundFragment extends Fragment {

    // UI elements
    RecyclerView itemLostAndFoundRecycler;

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
        LostAndFoundAdapter lostAndFoundAdapter = new LostAndFoundAdapter(getContext(), currentLostItems);
        itemLostAndFoundRecycler.setAdapter(lostAndFoundAdapter);
        itemLostAndFoundRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // We need to go into our ViewModel and bind to the LiveView
        // Since we only need to populate the recycle view once
        LostFoundViewModel lostFoundViewModel = new ViewModelProvider(requireActivity()).get(LostFoundViewModel.class);
        lostFoundViewModel.getAllLostAndFoundItems().observe(getViewLifecycleOwner(), items -> {
            currentLostItems.addAll(items);
            lostAndFoundAdapter.notifyDataSetChanged();
        });

        return view;
    }
}