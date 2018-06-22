package com.zawzaw.tedtalksassignment.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.adapters.WatchAdapter;
import com.zawzaw.tedtalksassignment.data.models.TalksModel;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.utils.GlideApp;

public class TalkDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_talk_description) TextView tvTalkDecription;
    @BindView(R.id.iv_talk_image) ImageView ivTalkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);

        ButterKnife.bind(this, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        int talkId = getIntent().getIntExtra("talksId", 0);
        Log.d("TalkDetailsActiviy", "talksId : " + talkId);

        TalksVO talks = TalksModel.getObjInstanceTalk().getTalksbyId(talkId);
        bindData(talks);

        RecyclerView rvWatchNext = findViewById(R.id.rv_watch_next);
        WatchAdapter watchAdapter = new WatchAdapter();
        rvWatchNext.setAdapter(watchAdapter);
        rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

    }

    private void bindData(TalksVO talks) {
        // Talk description
        tvTalkDecription.setText(talks.getDescription());

        // Talk header image
        GlideApp.with(ivTalkImage.getContext())
                .load(talks.getImageUrl())
                .into(ivTalkImage);

    }

}
