package com.andygeeks.bala;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andygeeks.bala.Pojo.CountryDetails;

import java.util.ArrayList;

public class ContinentsAdapter extends RecyclerView.Adapter<ContinentsAdapter.ViewHolder> {
    ArrayList<String> regionList;
    OnItemClick onItemClick;

    public ContinentsAdapter(ArrayList<String> regionList) {
        this.regionList = regionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_continent, null, false);
        return new ViewHolder(view);
    }

    public void setRegionClickListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.continent.setText(regionList.get(position));

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemclick(regionList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView continent;
        LinearLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            continent = itemView.findViewById(R.id.continent);
            rootView = itemView.findViewById(R.id.rootView);
        }

    }
}
