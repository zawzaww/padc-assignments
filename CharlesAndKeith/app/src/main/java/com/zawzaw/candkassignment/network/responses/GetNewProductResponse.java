package com.zawzaw.candkassignment.network.responses;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zawzaw.candkassignment.data.vos.NewProductVO;

public class GetNewProductResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<NewProductVO> newProduct;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<NewProductVO> getNewProduct() {
        return newProduct;
    }

    public String getPage() {
        return page;
    }

    public boolean isResponseOk() {
        return code == 200 && newProduct != null;
    }
}
