package com.zawzaw.tedtalksassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.delegates.TalksDelegate;
import com.zawzaw.tedtalksassignment.utils.GlideApp;

public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mTalksDelegate;

    private TalksVO mTalks;

    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakerName;
    @BindView(R.id.tv_talk_title)
    TextView tvTalkTitle;
    @BindView(R.id.iv_speaker_img)
    ImageView ivSpakerImage;

    public TalksViewHolder(View itemView, TalksDelegate talksDelegate) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        mTalksDelegate = talksDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalksDelegate.onTapTalks();
            }
        });
    }

    public void setTalksData(TalksVO talks) {
        mTalks = talks;

        // Speaker name
        tvSpeakerName.setText(talks.getSpeaker().getName());
        // Talk title
        tvTalkTitle.setText(talks.getTitle());
        // Speaker's header image
        GlideApp.with(ivSpakerImage.getContext())
                .load(talks.getImageUrl())
                .into(ivSpakerImage);
    }

}
