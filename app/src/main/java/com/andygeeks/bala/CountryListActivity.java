package com.andygeeks.bala;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.andygeeks.bala.Pojo.CountryDetails;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.Comparator;

import static com.andygeeks.bala.MainActivity.REGION;

public class CountryListActivity extends AppCompatActivity {

    RecyclerView countryListRecyclerView;
    String selectedRegion;
    Gson gson;
    CountryAdapter countryAdapter;
    CountryDetails[] countryDetails;
    boolean isSorted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countrylist);

        gson = new Gson();

        countryListRecyclerView = (RecyclerView)findViewById(R.id.countryListRecyclerView);
        countryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryListRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        selectedRegion = getIntent().getStringExtra(REGION);

        AndroidNetworking.get(Constants.URL_REGION)
                .addPathParameter(REGION,selectedRegion)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        countryDetails = gson.fromJson(response.toString(), CountryDetails[].class);
                        Handler handler = new Handler();
                        handler.post(runnable);
                    }

                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sortmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.sort) {
            Arrays.sort(countryDetails, new Comparator<CountryDetails>() {
                @Override
                public int compare(CountryDetails o1, CountryDetails o2) {
                    return o2.compareTo(o1);
                }
            });

            Handler handler = new Handler();
            handler.post(runnable);
        }

        return super.onOptionsItemSelected(item);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            countryAdapter = new CountryAdapter(countryDetails, CountryListActivity.this);
            countryListRecyclerView.setAdapter(countryAdapter);

            countryAdapter.setOnItemClick(new OnCountryClick() {
                @Override
                public void onCountryClick(CountryDetails countryDetails) {
                    Intent intent = new Intent(CountryListActivity.this, CountryDetailsActivity.class);
                    intent.putExtra("CountryDetails", countryDetails);
                    startActivity(intent);
                }
            });
        }
    };
}
