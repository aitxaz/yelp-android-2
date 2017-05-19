package com.example.androidtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtest.web_responses.search_resturant.Category;

import java.util.List;

/**
 * Created by RCAPPSMac3 on 19/05/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Category> categories;
    Context context;

    public Adapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("onBindViewHolder", "position: " + position);
        Category category = categories.get(position);

        holder.tvCategories.setText("" + category.getTitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategories;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategories = (TextView) itemView.findViewById(R.id.tvCategories);
        }
    }
}
