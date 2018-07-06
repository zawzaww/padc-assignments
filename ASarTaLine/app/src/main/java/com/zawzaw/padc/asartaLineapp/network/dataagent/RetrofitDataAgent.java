package com.zawzaw.padc.asartaLineapp.network.dataagent;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.zawzaw.padc.asartaLineapp.events.ApiErrorEvent;
import com.zawzaw.padc.asartaLineapp.events.SuccessGetWarDeeEvent;
import com.zawzaw.padc.asartaLineapp.network.WarDeeRetrofitApi;
import com.zawzaw.padc.asartaLineapp.network.response.GetWarDeeResponse;
import com.zawzaw.padc.asartaLineapp.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class RetrofitDataAgent implements WarDeeDataAgent {

    private static RetrofitDataAgent objectInstance;

    private WarDeeRetrofitApi mApi;

    private static final String EMPTY_API_ERROR = "Empty in this body!";

    public RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(AppConstants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(WarDeeRetrofitApi.class);
    }

    public static RetrofitDataAgent getObjectInstance() {
        if (objectInstance == null) {
            objectInstance = new RetrofitDataAgent();
        }
        return objectInstance;
    }

    @Override
    public void loadWarDee(String accessToken) {

        Call<GetWarDeeResponse> warDeeCall = mApi.loadWarDee(accessToken);

        warDeeCall.enqueue(new Callback<GetWarDeeResponse>() {
            @Override
            public void onResponse(Call<GetWarDeeResponse> call, Response<GetWarDeeResponse> response) {
                GetWarDeeResponse warDeeResponse = response.body();
                if (warDeeResponse != null && warDeeResponse.isResponseOk()) {
                    SuccessGetWarDeeEvent event = new SuccessGetWarDeeEvent(warDeeResponse.getAstlWarDee());
                    EventBus.getDefault().post(event);
                } else {

                    if (warDeeResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(EMPTY_API_ERROR);
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(warDeeResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetWarDeeResponse> call, Throwable t) {

                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });

    }

}
