package com.zawzaw.padc.mmkunyi.data.models

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO
import com.zawzaw.padc.mmkunyi.events.ApiErrorEvent
import com.zawzaw.padc.mmkunyi.events.SuccessGetJobsEvent
import com.zawzaw.padc.mmkunyi.network.dataagent.JobsDataAgent
import com.zawzaw.padc.mmkunyi.network.dataagent.RetrofitDataAgent

/**
 * Created by zawzaw on 02/08/2018.
 */

class JobsModel {

    companion object {

        private var objIntance: JobsModel? = null

        private var mDataAgent: JobsDataAgent? = null

        const val DUMMY_ACCESS_TOKEY: String = "b002c7e1a528b7cb460933fc2875e916"

        fun getObjIntance(): JobsModel? {
            if (objIntance == null) {
                objIntance = JobsModel()
            }
            return objIntance
        }
    }

    private var mPage: Int = 1

    private var mDataRepo: HashMap<Int, JobsVO> = HashMap()

    private constructor() {
        mDataAgent = RetrofitDataAgent.getObjInstance()
        EventBus.getDefault().register(this)
    }

    fun loadJobsList() {
        mDataAgent!!.loadJobsList(DUMMY_ACCESS_TOKEY, mPage, false)
        mPage += 1
    }

    fun forceRefreshJobList() {
        mPage = 1
        mDataAgent!!.loadJobsList(DUMMY_ACCESS_TOKEY, mPage, true)
    }

    fun setJobsDataRepo(mJobsList: List<JobsVO>) {
        for (jobs: JobsVO in mJobsList)
            mDataRepo[jobs.jobPostId] = jobs
    }

    fun getJobsById(id: Int): JobsVO? {
        return mDataRepo[id]
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobs(successEvent: SuccessGetJobsEvent) {
        setJobsDataRepo(successEvent.jobsList)

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onFailureGetJobs(apiErrorEvent: ApiErrorEvent) {

    }

}