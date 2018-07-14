package com.zawzaw.padc.mmhealthcare.data.models

import com.zawzaw.padc.mmhealthcare.network.dataagent.HealthDataAgent
import com.zawzaw.padc.mmhealthcare.network.dataagent.RetrofitDataAgent

/**
 * Created by zawzaw on 14/07/2018.
 */

class HealthModel {

    companion object {
        private var objInstance: HealthModel? = null

        private var mDataAgent: HealthDataAgent? = null

        private var accessToken: String = "b002c7e1a528b7cb460933fc2875e916"

        fun getObjInstance(): HealthModel? {
            if (objInstance == null) {
                objInstance = HealthModel()
            }
            return objInstance
        }
    }

    private constructor() {
        mDataAgent = RetrofitDataAgent.getObjectInstance()
    }

    fun loadHealthCareInfo() {
        mDataAgent!!.loadHealthCareInfo(accessToken)
    }

}
