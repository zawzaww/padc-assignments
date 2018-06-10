package com.zawzaw.tedtalksassignment.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.adapters.WatchAdapter;

public class TalkDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        RecyclerView rvWatchNext = findViewById(R.id.rv_watch_next);
        WatchAdapter watchAdapter = new WatchAdapter();
        rvWatchNext.setAdapter(watchAdapter);

        rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }
}
