package com.zawzaw.padc.asartaLineapp.data.models;

import com.zawzaw.padc.asartaLineapp.network.dataagent.RetrofitDataAgent;
import com.zawzaw.padc.asartaLineapp.network.dataagent.WarDeeDataAgent;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class WarDeeModel {

    private static WarDeeModel objInstance;

    private WarDeeDataAgent mDataAgent;

    private static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    public WarDeeModel() {
        mDataAgent = RetrofitDataAgent.getObjectInstance();
    }

    public static WarDeeModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new WarDeeModel();
        }
        return objInstance;
    }

    public void loadWarDeeList() {
        mDataAgent.loadWarDee(ACCESS_TOKEN);
    }

}
