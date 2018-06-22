package com.zawzaw.tedtalksassignment.data.models;

import java.util.HashMap;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.zawzaw.tedtalksassignment.data.vos.TalksVO;
import com.zawzaw.tedtalksassignment.events.SuccessGetTalksEvent;
import com.zawzaw.tedtalksassignment.network.dataagents.RetrofitDataAgent;
import com.zawzaw.tedtalksassignment.network.dataagents.TalksDataAgent;

/**
 * Created by zawzaw on 15/06/2018.
 */

public class TalksModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TalksDataAgent mDataAgent;

    private static TalksModel objInstanceTalk;

    private Map<Integer, TalksVO> mTalksMap;

    private TalksModel() {
        // mDataAgent = HttpUrlConnectionDataAgent.getObjInstanceHttp();
        // mDataAgent = OkHttpDataAgent.getObjInstance();
        mDataAgent = RetrofitDataAgent.getObjInstance();

        mTalksMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static TalksModel getObjInstanceTalk() {
        if (objInstanceTalk == null) {
            objInstanceTalk = new TalksModel();
        }
        return objInstanceTalk;
    }

    public void loadTalksList() {
        mDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);
    }

    public TalksVO getTalksbyId(int talksId) {
        return mTalksMap.get(talksId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTalks(SuccessGetTalksEvent event) {

        for (TalksVO talks : event.getTalksList()) {
            mTalksMap.put(talks.getTalkId(), talks);
        }

    }

}
