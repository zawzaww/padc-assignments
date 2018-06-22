package com.zawzaw.tedtalksassignment.network.dataagents;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.zawzaw.tedtalksassignment.events.ApiErrorEvent;
import com.zawzaw.tedtalksassignment.events.SuccessGetTalksEvent;
import com.zawzaw.tedtalksassignment.network.responses.GetTalksResponse;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

/**
 * Created by zawzaw on 15/06/2018.
 */

public class HttpUrlConnectionDataAgent implements TalksDataAgent {

    private static HttpUrlConnectionDataAgent objInstanceHttp;

    private HttpUrlConnectionDataAgent() {

    }

    public static HttpUrlConnectionDataAgent getObjInstanceHttp() {
        if (objInstanceHttp == null) {
            objInstanceHttp = new HttpUrlConnectionDataAgent();
        }
        return objInstanceHttp;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>() {

            URL url;
            BufferedReader reader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    url = new URL(TalksConstants.BASE_API + TalksConstants.GET_TALKS);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(13 * 1000);

                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TalksConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TalksConstants.PARM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString();
                    return responseString;

                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    e.printStackTrace();

                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            Log.e("ERROR", ioe.getMessage());
                            ioe.printStackTrace();
                        }
                    }
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

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {

        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;

        for (NameValuePair pair : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append("&");
            }

            stringBuilder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return stringBuilder.toString();
    }

}
