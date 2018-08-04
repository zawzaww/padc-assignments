package com.zawzaw.padc.mmkunyi.network.response

import com.google.gson.annotations.SerializedName
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO

/**
 * Created by zawzaw on 02/08/2018.
 */

class GetJobsResponse {

    @SerializedName("code")
    val code: Int = 0

    @SerializedName("message")
    val message: String? = null

    @SerializedName("apiVersion")
    val apiVersion: String? = null

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("jobs")
    private var jobsList: List<JobsVO>? = null

    fun getJobsList(): List<JobsVO> {
        if (jobsList == null) {
            jobsList = ArrayList<JobsVO>()
        }
        val jobsListVal = jobsList
        return jobsListVal!!
    }

    fun isResponseOk(): Boolean {
        return code == 200 && getJobsList().isNotEmpty() && jobsList != null
    }

}