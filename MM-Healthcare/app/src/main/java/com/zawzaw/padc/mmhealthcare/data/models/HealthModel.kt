package com.zawzaw.padc.mmhealthcare.data.models

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO
import com.zawzaw.padc.mmhealthcare.events.SuccessGetHealthEvent
import com.zawzaw.padc.mmhealthcare.network.dataagent.HealthDataAgent
import com.zawzaw.padc.mmhealthcare.network.dataagent.RetrofitDataAgent

/**
 * Created by zawzaw on 14/07/2018.
 */

class HealthModel {

    companion object {

        private var objInstance: HealthModel? = null

        private var mDataAgent: HealthDataAgent? = null

        const val ACCESS_TOKEN: String = "b002c7e1a528b7cb460933fc2875e916"

        fun getObjInstance(): HealthModel? {
            if (objInstance == null) {
                objInstance = HealthModel()
            }
            return objInstance
        }
    }

    private var mDataRepo: HashMap<Int, HealthCareVO> = HashMap()

    private constructor() {
        mDataAgent = RetrofitDataAgent.getObjectInstance()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthInfo(event: SuccessGetHealthEvent) {
        setHealthInfoDataRepo(event.healthList)
    }

    fun setHealthInfoDataRepo(mHealthList: List<HealthCareVO>) {
        for (healthCare: HealthCareVO in mHealthList)
            mDataRepo[healthCare.id] = healthCare
    }

    fun getHealthInfoById(id: Int): HealthCareVO? {
        return mDataRepo[id]

    }

    fun loadHealthCareInfo() {
        mDataAgent!!.loadHealthCareInfo(ACCESS_TOKEN)

    }

}
