package com.zawzaw.tedtalksassignment.network.dataagents;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.zawzaw.tedtalksassignment.events.ApiErrorEvent;
import com.zawzaw.tedtalksassignment.events.SuccessGetTalksEvent;
import com.zawzaw.tedtalksassignment.network.responses.GetTalksResponse;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zawzaw on 22/06/2018.
 */

public class OkHttpDataAgent implements TalksDataAgent {

    private static OkHttpDataAgent objInstance;

    private OkHttpClient mHttpClient;

    private OkHttpDataAgent() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgent getObjInstance() {
        if (objInstance == null) {
            objInstance = new OkHttpDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                RequestBody formBody = new FormBody.Builder()
                        .add(TalksConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TalksConstants.PARAM_PAGE, String.valueOf(page))
                        .build();

                Request request = new Request.Builder()
                        .url(TalksConstants.BASE_API + TalksConstants.GET_TALKS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }

                } catch (IOException e) {
                    Log.e("LoadTalksList", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);

                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(responseString, GetTalksResponse.class);
                Log.d("OnPostExecute", "Talks List Size : " + talksResponse.getmTalks().size());

                if (talksResponse.isResponseOk()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getmTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }

        }.execute();
    }

}
