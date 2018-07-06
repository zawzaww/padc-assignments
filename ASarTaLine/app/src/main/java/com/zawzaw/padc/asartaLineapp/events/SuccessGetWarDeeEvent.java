package com.zawzaw.padc.asartaLineapp.events;

import java.util.List;

import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class SuccessGetWarDeeEvent {

    private List<WarDeeVO> mWarDeeList;

    public SuccessGetWarDeeEvent(List<WarDeeVO> mWarDeeList) {
        this.mWarDeeList = mWarDeeList;
    }

    public List<WarDeeVO> getmWarDeeList() {
        return mWarDeeList;
    }

}
