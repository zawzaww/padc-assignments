package com.zawzaw.tedtalksassignment.network.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zawzaw.tedtalksassignment.data.vos.TalksVO;

/**
 * Created by zawzaw on 22/06/2018.
 */

public class GetTalksResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("ted_talks")
    private List<TalksVO> mTalks;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TalksVO> getmTalks() {
        return mTalks;
    }

    public boolean isResponseOk() {
        return (code == 200 && mTalks != null);
    }

}
