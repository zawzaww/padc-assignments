package com.zawzaw.padc.mmkunyi.data.models

import com.zawzaw.padc.mmkunyi.data.vos.JobsVO
import com.zawzaw.padc.mmkunyi.events.SuccessGetJobsEvent
import com.zawzaw.padc.mmkunyi.network.dataagent.JobsDataAgent
import com.zawzaw.padc.mmkunyi.network.dataagent.RetrofitDataAgent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by zawzaw on 02/08/2018.
 */

class JobsModel {

    companion object {

        private var objIntance: JobsModel? = null

        private var mDataAgent: JobsDataAgent? = null

        const val DUMMY_ACCESS_TOKEY: String = "b002c7e1a528b7cb460933fc2875e916"
        const val DUMMY_PAGE: Int = 1

        fun getObjIntance(): JobsModel? {
            if (objIntance == null) {
                objIntance = JobsModel()
            }
            return objIntance
        }
    }

    private var mDataRepo: HashMap<Int, JobsVO> = HashMap()

    private constructor() {
        mDataAgent = RetrofitDataAgent.getObjInstance()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobs(event: SuccessGetJobsEvent) {
        setJobsDataRepo(event.jobsList)
    }

    fun setJobsDataRepo(mJobsList: List<JobsVO>) {
        for (jobs: JobsVO in mJobsList)
            mDataRepo[jobs.jobPostId] = jobs
    }

    fun getJobsById(jobsId: Int): JobsVO? {
        return mDataRepo.get(jobsId)
    }

    fun loadJobs() {
        mDataAgent!!.loadJobsList(DUMMY_ACCESS_TOKEY, DUMMY_PAGE)
    }

}