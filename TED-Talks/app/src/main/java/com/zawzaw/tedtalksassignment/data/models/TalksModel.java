package com.zawzaw.tedtalksassignment.data.models;

import com.zawzaw.tedtalksassignment.network.dataagents.OkHttpDataAgent;
import com.zawzaw.tedtalksassignment.network.dataagents.TalksDataAgent;

/**
 * Created by zawzaw on 15/06/2018.
 */

public class TalksModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TalksDataAgent mDataAgent;

    private static TalksModel objInstanceTalk;

    private TalksModel() {
        // mDataAgent = HttpUrlConnectionDataAgent.getObjInstanceHttp();
        mDataAgent = OkHttpDataAgent.getObjInstance();
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

}
