package com.zawzaw.padc.mmhealthcare.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.zawzaw.padc.mmhealthcare.network.responses.GetHealthResponse
import com.zawzaw.padc.mmhealthcare.utils.AppConstants

/**
 * Created by zawzaw on 14/07/2018.
 */

interface RetrofitHealthApi {

    @FormUrlEncoded
    @POST(AppConstants.GET_HEALTH_INFO)
    fun loadHealthCareInfo(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) accessToken:
            String): Call<GetHealthResponse>

}
