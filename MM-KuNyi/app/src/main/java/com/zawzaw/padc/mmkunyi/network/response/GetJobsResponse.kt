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
    val message: String = " "

    @SerializedName("apiVersion")
    val apiVersion: String = " "

    @SerializedName("jobs")
    val jobsList: List<JobsVO>? = null

    fun isResponseOk(): Boolean {
        return code == 200 && jobsList != null
    }

}