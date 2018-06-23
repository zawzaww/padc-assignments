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

import com.bumptech.glide.request.RequestOptions;
import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.adapters.WatchAdapter;
import com.zawzaw.tedtalksassignment.data.models.TalksModel;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.utils.GlideApp;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

public class TalkDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_talk_description) TextView tvTalkDecription;
    @BindView(R.id.iv_talk_image) ImageView ivTalkImage;
    @BindView(R.id.tv_speaker) TextView tvSpeaker;
    @BindView(R.id.tv_talk_title) TextView tvTalkTitle;
    @BindView(R.id.tv_talk_duration) TextView tvDuration;
    @BindView(R.id.iv_profile_pic) ImageView ivSpeakerProfileImage;
    @BindView(R.id.tv_author_name) TextView tvAuthorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        ButterKnife.bind(this, this);

        int talkId = getIntent().getIntExtra(TalksConstants.TAlKS_ID, 0);
        Log.d("TalkDetailsActiviy", "talksId : " + talkId);

        RecyclerView rvWatchNext = findViewById(R.id.rv_watch_next);
        WatchAdapter watchAdapter = new WatchAdapter();
        rvWatchNext.setAdapter(watchAdapter);
        rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        TalksVO talks = TalksModel.getObjInstanceTalk().getTalksbyId(talkId);
        bindData(talks);

    }

    private void bindData(TalksVO talks) {
        // Talk description.
        tvTalkDecription.setText(talks.getDescription());

        // Talk header image.
        GlideApp.with(ivTalkImage.getContext())
                .load(talks.getImageUrl())
                .into(ivTalkImage);

        // Speaker name.
        tvSpeaker.setText(talks.getSpeaker().getName());

        // Talk title.
        tvTalkTitle.setText(talks.getTitle());

        // Talk duration.
        tvDuration.setText(secToString(talks.getDurationInSec()));

        // Speaker Profile Image.
        GlideApp.with(ivSpeakerProfileImage.getContext())
                .load(talks.getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(ivSpeakerProfileImage);

        // About the speaker
        tvAuthorName.setText(talks.getSpeaker().getName());

    }

    public String secToString(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

}
