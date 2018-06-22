package com.zawzaw.tedtalksassignment.network.dataagents;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import org.greenrobot.eventbus.EventBus;

import com.zawzaw.tedtalksassignment.events.ApiErrorEvent;
import com.zawzaw.tedtalksassignment.events.SuccessGetTalksEvent;
import com.zawzaw.tedtalksassignment.network.responses.GetTalksResponse;
import com.zawzaw.tedtalksassignment.network.retrofit.RetrofitTalksApi;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

/**
 * Created by zawzaw on 22/06/2018.
 */

public class RetrofitDataAgent implements TalksDataAgent {

    private static RetrofitDataAgent objInstance;

    private RetrofitTalksApi mTalksApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TalksConstants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTalksApi = retrofit.create(RetrofitTalksApi.class);
    }

    public static RetrofitDataAgent getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(int page, String accessToken) {

        Call<GetTalksResponse> loadTalksCall = mTalksApi.loadTalksList(accessToken, page);
        loadTalksCall.enqueue(new Callback<GetTalksResponse>() {

            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {

                GetTalksResponse talksResponse = response.body();

                if (talksResponse != null && talksResponse.isResponseOk()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getmTalks());
                    EventBus.getDefault().post(event);
                } else {

                    if (talksResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty response");
                        EventBus.getDefault().post(errorEvent);
                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(talksResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {
                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

}
