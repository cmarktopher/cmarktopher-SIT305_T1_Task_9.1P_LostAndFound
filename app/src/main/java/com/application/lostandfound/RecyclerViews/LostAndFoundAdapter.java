package com.application.lostandfound.RecyclerViews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.application.lostandfound.Fragments.ItemInformationFragment;
import com.application.lostandfound.Fragments.ShowLostAndFoundFragment;
import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.ViewModels.LostFoundViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This will be for our recycler view showing all of the lost and found items
 */
public class LostAndFoundAdapter extends RecyclerView.Adapter<LostAndFoundAdapter.LostAndFoundItemView> {

    private Context context;
    private List<LostFoundDataModel> items = new ArrayList<LostFoundDataModel>();

    public LostAndFoundAdapter(Context context, List<LostFoundDataModel> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public LostAndFoundAdapter.LostAndFoundItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lost_found_item_card, parent, false);

        return new LostAndFoundItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostAndFoundAdapter.LostAndFoundItemView holder, int position) {

        LostFoundDataModel item = items.get(position);

        // Update the view's text to the respective item name and item lost/found state.
        holder.getItemNameView().setText(item.itemState + " " + item.name);

        // Bind an on click listener to transition to the item information fragment when a card has been pressed.
        holder.getItemCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // So, we have a situation here where we need to get the item information into the fragment we are transitioning to.
                // An easy we can do this is by utilizing the view model that was created to interact with the database.
                // This is probably not a good way to do this, but it is the easiest.
                LostFoundViewModel lostFoundViewModel = new ViewModelProvider((AppCompatActivity)context).get(LostFoundViewModel.class);
                lostFoundViewModel.setCurrentItemToView(item);

                // Perform the transition
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                        .setReorderingAllowed(true)
                        .replace(R.id.coreFragmentContainer, ItemInformationFragment.newInstance(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    /**
     * View for the lost or found item
     */
    public class LostAndFoundItemView extends RecyclerView.ViewHolder {

        private final TextView itemNameView;
        private final CardView itemCardView;

        public LostAndFoundItemView(@NonNull View itemView) {

            super(itemView);

            itemNameView = itemView.findViewById(R.id.itemNameView);
            itemCardView = itemView.findViewById(R.id.itemCardView);
        }

        public TextView getItemNameView() {
            return itemNameView;
        }
        public CardView getItemCardView() {
            return itemCardView;
        }
    }
}
