package com.andygeeks.bala;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andygeeks.bala.Pojo.CountryDetails;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String REGION = "region";
    private RecyclerView mRecyclerView;
    private ContinentsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> regions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        regions= new ArrayList(Arrays.asList(Constants.REGIONS));
        mAdapter = new ContinentsAdapter(regions);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setRegionClickListener(new OnItemClick() {
            @Override
            public void onItemclick(String region) {
                Intent intent = new Intent(MainActivity.this, CountryListActivity.class);
                intent.putExtra(REGION, region);
                startActivity(intent);
            }
        });
    }
}
