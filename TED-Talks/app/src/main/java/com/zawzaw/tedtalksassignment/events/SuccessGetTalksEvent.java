package com.zawzaw.tedtalksassignment.events;

import java.util.List;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;

/**
 * Created by zawzaw on 22/06/2018.
 */

public class SuccessGetTalksEvent {

    private List<TalksVO> talksList;

    public SuccessGetTalksEvent(List<TalksVO> talksList) {
        this.talksList = talksList;
    }

    public List<TalksVO> getTalksList() {
        return talksList;
    }

}
