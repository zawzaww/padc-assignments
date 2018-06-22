package com.zawzaw.tedtalksassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.delegates.TalksDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mTalksDelegate;

    private TalksVO mTalks;

    @BindView(R.id.tv_speaker_name) TextView tvSpeakerName;
    @BindView(R.id.tv_talk_title) TextView tvTalkTitle;

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

        tvSpeakerName.setText(talks.getSpeaker().getName());
        tvTalkTitle.setText(talks.getTitle());

    }

}
