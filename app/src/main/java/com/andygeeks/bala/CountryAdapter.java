package com.andygeeks.bala;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andygeeks.bala.Pojo.CountryDetails;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private CountryDetails[] countryDetails;
    OnCountryClick onCountryClick;

    public CountryAdapter(CountryDetails[] countryDetails, Context mContext) {
        this.countryDetails = countryDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_country, null, false);
        return new ViewHolder(view);
    }

    public void setOnItemClick(OnCountryClick onCountryClick) {
        this.onCountryClick = onCountryClick;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.countryName.setText(countryDetails[position].getName());
        holder.countryCapitalName.setText("Capital: "+countryDetails[position].getCapital());
        holder.bordersCount.setText("Borders "+String.valueOf(countryDetails[position].getBorders().length));
        Uri uri = Uri.parse(countryDetails[position].getFlag());

        MyApplication.getRequestBuilder()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(holder.flagImageView);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCountryClick.onCountryClick(countryDetails[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryDetails.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        TextView countryCapitalName;
        TextView bordersCount;
        ImageView flagImageView;
        RelativeLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            countryCapitalName = itemView.findViewById(R.id.countryCapitalName);
            bordersCount = itemView.findViewById(R.id.bordersCount);
            rootView = itemView.findViewById(R.id.rootView);
            flagImageView = (ImageView)itemView.findViewById(R.id.flagImageView);
        }

    }
}
