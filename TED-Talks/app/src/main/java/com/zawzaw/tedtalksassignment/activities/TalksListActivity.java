package com.zawzaw.tedtalksassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.adapters.TalksAdapter;
import com.zawzaw.tedtalksassignment.data.models.TalksModel;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.delegates.TalksDelegate;
import com.zawzaw.tedtalksassignment.events.SuccessGetTalksEvent;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

public class TalksListActivity extends BaseActivity implements TalksDelegate {

    private TalksAdapter mTalksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talks_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvTalks = findViewById(R.id.rv_talks);
        mTalksAdapter = new TalksAdapter(this);
        rvTalks.setAdapter(mTalksAdapter);
        rvTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        TalksModel.getObjInstanceTalk().loadTalksList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapTalks(TalksVO talks) {
        Intent intent = new Intent(getApplicationContext(), TalkDetailsActivity.class);
        intent.putExtra(TalksConstants.TAlKS_ID, talks.getTalkId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTalks(SuccessGetTalksEvent event) {
        Log.d("OnSuccessGetTalks", "Success Talks List : " + event.getTalksList().size());

        mTalksAdapter.setTalksList(event.getTalksList());
    }

}
