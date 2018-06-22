package com.zawzaw.tedtalksassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.zawzaw.tedtalksassignment.R;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.delegates.TalksDelegate;
import com.zawzaw.tedtalksassignment.viewholders.TalksViewHolder;

public class TalksAdapter extends RecyclerView.Adapter<TalksViewHolder> {

    private TalksDelegate mTalksDelegate;

    private List<TalksVO> mTalksList;

    public TalksAdapter(TalksDelegate talksDelegate) {
        mTalksDelegate = talksDelegate;
        mTalksList = new ArrayList<>();
    }

    @Override
    public TalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talks, parent, false);
        return new TalksViewHolder(view, mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(TalksViewHolder holder, int position) {
        holder.setTalksData(mTalksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTalksList.size();
    }

    public void setTalksList(List<TalksVO> mTalksList) {
        this.mTalksList = mTalksList;
        notifyDataSetChanged();
    }

}
