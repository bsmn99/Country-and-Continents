package com.andygeeks.bala;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.andygeeks.bala.Pojo.CountryDetails;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CountryDetailsActivity extends AppCompatActivity {

    ImageView flagImageView;
    TextView subregion, population, numericCode;
    CountryDetails countryDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countrydetails);

        flagImageView = (ImageView) findViewById(R.id.flagImageView);
        subregion = (TextView)findViewById(R.id.subregion);
        population = (TextView)findViewById(R.id.population);
        numericCode = (TextView)findViewById(R.id.numericCode);

        countryDetails = (CountryDetails) getIntent().getSerializableExtra("CountryDetails");

        Uri uri = Uri.parse(countryDetails.getFlag());
        MyApplication.getRequestBuilder()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(flagImageView);

        subregion.setText(countryDetails.getSubregion());
        population.setText(countryDetails.getPopulation());
        numericCode.setText(String.valueOf(countryDetails.getNumericCode()));
    }
}
