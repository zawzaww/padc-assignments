package com.zawzaw.tedtalksassignment.network.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.zawzaw.tedtalksassignment.network.responses.GetTalksResponse;
import com.zawzaw.tedtalksassignment.utils.TalksConstants;

/**
 * Created by zawzaw on 22/06/2018.
 */

public interface RetrofitTalksApi {

    @FormUrlEncoded
    @POST(TalksConstants.GET_TALKS)
    Call<GetTalksResponse> loadTalksList(
            @Field(TalksConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TalksConstants.PARAM_PAGE) int page);

}
