package com.kamila.searchfilterrecyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kamila.searchfilterrecyclerview.R;
import com.kamila.searchfilterrecyclerview.models.Celebrity;

import java.util.ArrayList;
import java.util.List;

public class CelebrityRecyclerViewAdapter extends RecyclerView.Adapter<CelebrityRecyclerViewAdapter.MyViewHolder> implements Filterable {

    private List<Celebrity> celebrityList;
    private List<Celebrity> celebrityListFiltered;
    private Context context;

    public CelebrityRecyclerViewAdapter(Context context, List<Celebrity> items) {
        celebrityList = items;
        celebrityListFiltered = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Celebrity celebrity = celebrityListFiltered.get(position);
        holder.name.setText(celebrity.getName());

        Glide.with(context)
                .load(celebrity.getImageUrl())
                .centerCrop()
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return celebrityListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    celebrityListFiltered = celebrityList;
                } else {
                    List<Celebrity> filteredList = new ArrayList<>();
                    for (Celebrity celebrity : celebrityList) {

                        if (celebrity.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(celebrity);
                        }
                    }

                    celebrityListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = celebrityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                celebrityListFiltered = (ArrayList<Celebrity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });
        }
    }
}