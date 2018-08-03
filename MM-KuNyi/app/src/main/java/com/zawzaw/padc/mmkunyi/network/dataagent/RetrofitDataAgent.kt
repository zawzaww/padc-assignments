package com.zawzaw.padc.mmkunyi.network.dataagent

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.greenrobot.eventbus.EventBus
import com.zawzaw.padc.mmkunyi.utils.AppConstants
import com.zawzaw.padc.mmkunyi.events.ApiErrorEvent
import com.zawzaw.padc.mmkunyi.events.SuccessGetJobsEvent
import com.zawzaw.padc.mmkunyi.network.RetrofitJobsApi
import com.zawzaw.padc.mmkunyi.network.response.GetJobsResponse

/**
 * Created by zawzaw on 02/08/2018.
 */

class RetrofitDataAgent : JobsDataAgent {

    companion object {
        private var objInstance: RetrofitDataAgent? = null

        fun getObjInstance(): RetrofitDataAgent? {
            if (objInstance == null) {
                objInstance = RetrofitDataAgent()
            }
            return objInstance
        }
    }

    private var mApi: RetrofitJobsApi? = null

    private constructor() {

        val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(mOkHttpClient)
                .build()

        mApi = retrofit.create(RetrofitJobsApi::class.java)
    }

    override fun loadJobsList(accessToken: String, page: Int) {

        var jobsApiCall: Call<GetJobsResponse> = mApi!!.loadJobs(accessToken, page)
        jobsApiCall.enqueue(object : Callback<GetJobsResponse> {

            override fun onResponse(call: Call<GetJobsResponse>?, response: Response<GetJobsResponse>?) {
                val jobsResponse: GetJobsResponse? = response!!.body()

                if (jobsResponse != null && jobsResponse.isResponseOk()) {
                    val successEvent = SuccessGetJobsEvent(jobsResponse.jobsList!!)
                    EventBus.getDefault().post(successEvent)
                } else {

                    if (jobsResponse == null) {
                        val errorEvent = ApiErrorEvent("Empty in Response")
                        EventBus.getDefault().post(errorEvent)

                    } else {
                        val errorEvent = ApiErrorEvent(jobsResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }
            }

            override fun onFailure(call: Call<GetJobsResponse>?, t: Throwable?) {
                val errorEvent = ApiErrorEvent(t!!.message!!)
                EventBus.getDefault().post(errorEvent)
            }
        })
    }

}