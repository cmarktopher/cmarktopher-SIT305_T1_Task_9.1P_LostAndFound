package com.application.lostandfound.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.application.lostandfound.Databases.LostAndFoundDatabase;
import com.application.lostandfound.Models.FoundDataModel;
import com.application.lostandfound.Models.LostDataModel;
import com.application.lostandfound.R;

import java.util.List;


public class ShowLostAndFoundFragment extends Fragment {

    private List<LostDataModel> lostItems;
    private List<FoundDataModel> foundItems;

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
        LinearLayout itemLostAndFoundContainer = view.findViewById(R.id.itemLostAndFoundContainer);

        // We need to reach into the database, and get all lost and found items.
        // The plan is to create cards (with recycler view) to display the name of the item.
        // It would make sense to just query the name column.
        // However, we need to be able to click the card and display information in a separate fragment.
        // So, the easiest option I can think off at the moment, is to grab the entire data model and pass that into the card.

        LostAndFoundDatabase.getDatabase(getContext()).getDatabaseWriteExecutor().execute(() -> {

            lostItems = LostAndFoundDatabase.getDatabase(getContext()).lostDao().getAllLostItems();
            foundItems = LostAndFoundDatabase.getDatabase(getContext()).foundDao().getAllFoundItems();

        });


        return view;
    }
}