package com.application.lostandfound.RecyclerViews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.lostandfound.Models.LostFoundDataModel;
import com.application.lostandfound.R;
import com.application.lostandfound.ViewModels.LostFoundViewModel;

import java.util.ArrayList;
import java.util.List;

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
        holder.getItemNameView().setText(item.itemState + " " + item.name);
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

        public LostAndFoundItemView(@NonNull View itemView) {

            super(itemView);
            itemNameView = itemView.findViewById(R.id.itemNameView);
        }

        public TextView getItemNameView() {
            return itemNameView;
        }
    }
}
