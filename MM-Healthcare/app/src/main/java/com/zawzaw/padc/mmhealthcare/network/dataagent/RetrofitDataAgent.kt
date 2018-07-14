package com.zawzaw.padc.mmhealthcare.network.dataagent

import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.zawzaw.padc.mmhealthcare.events.ApiErrorEvent
import com.zawzaw.padc.mmhealthcare.events.SuccessGetHealthEvent
import com.zawzaw.padc.mmhealthcare.network.RetrofitHealthApi
import com.zawzaw.padc.mmhealthcare.network.responses.GetHealthResponse
import com.zawzaw.padc.mmhealthcare.utils.AppConstants

/**
 * Created by zawzaw on 14/07/2018.
 */

class RetrofitDataAgent : HealthDataAgent {

    companion object {
        private var objInstance: RetrofitDataAgent? = null

        fun getObjectInstance(): RetrofitDataAgent? {
            if (objInstance == null) {
                objInstance = RetrofitDataAgent()
            }
            return objInstance
        }
    }

    private var mApi: RetrofitHealthApi? = null

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

        mApi = retrofit.create(RetrofitHealthApi::class.java)
    }

    override fun loadHealthCareInfo(accessToken: String) {

        var healthApiCall: Call<GetHealthResponse> = mApi!!.loadHealthCareInfo(accessToken)
        healthApiCall.enqueue(object : Callback<GetHealthResponse> {

            override fun onResponse(call: Call<GetHealthResponse>?, response: Response<GetHealthResponse>?) {
                val healthResponse: GetHealthResponse? = response!!.body()

                if (healthResponse != null && healthResponse.isResponseOK()) {
                    val successEvent = SuccessGetHealthEvent(healthResponse.healthCareList!!)
                    EventBus.getDefault().post(successEvent)
                } else {

                    if (healthResponse == null) {
                        val errorEvent = ApiErrorEvent("Empty in Response!")
                        EventBus.getDefault().post(errorEvent)
                    } else {
                        val errorEvent = ApiErrorEvent(healthResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }
            }

            override fun onFailure(call: Call<GetHealthResponse>?, t: Throwable?) {
                val errorEvent = ApiErrorEvent(t!!.message!!)
                EventBus.getDefault().post(errorEvent)
            }
        })
    }

}