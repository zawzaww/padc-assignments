package com.zawzaw.padc.asartaLineapp.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.zawzaw.padc.asartaLineapp.network.response.GetWarDeeResponse;
import com.zawzaw.padc.asartaLineapp.utils.AppConstants;

/**
 * Created by zawzaw on 06/07/2018.
 */

public interface WarDeeRetrofitApi {

    @FormUrlEncoded
    @POST(AppConstants.GET_WAR_DEE)
    Call<GetWarDeeResponse> loadWarDee (
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String accessToken);
}
