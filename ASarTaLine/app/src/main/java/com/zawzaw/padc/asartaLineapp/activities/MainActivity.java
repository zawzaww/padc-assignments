package com.zawzaw.padc.asartaLineapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.adapters.MainAdapter;

public class MainActivity extends BaseActivity {

    private MainAdapter mAdapter;

    @BindView(R.id.rv_asartaline_main)
    RecyclerView rvASarTaLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        mAdapter = new MainAdapter();
        rvASarTaLine.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        rvASarTaLine.setAdapter(mAdapter);

    }

}