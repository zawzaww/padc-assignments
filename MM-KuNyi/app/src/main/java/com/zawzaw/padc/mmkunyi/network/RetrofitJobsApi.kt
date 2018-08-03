package com.zawzaw.padc.mmkunyi.network

import com.zawzaw.padc.mmkunyi.network.response.GetJobsResponse
import com.zawzaw.padc.mmkunyi.utils.AppConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zawzaw on 02/08/2018.
 */

interface RetrofitJobsApi {

    @FormUrlEncoded
    @POST(AppConstants.GET_JOBS_URL)
    fun loadJobs(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) accessToken: String,
            @Field(AppConstants.PARAM_PAGE) page: Int): Call<GetJobsResponse>

}