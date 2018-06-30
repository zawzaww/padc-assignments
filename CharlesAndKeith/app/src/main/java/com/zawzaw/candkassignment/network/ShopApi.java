package com.zawzaw.candkassignment.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.zawzaw.candkassignment.network.responses.GetNewProductResponse;
import com.zawzaw.candkassignment.utils.ProductsConstants;

public interface ShopApi {

    @FormUrlEncoded
    @POST(ProductsConstants.GET_NEW_PRODUCTS)
    Call<GetNewProductResponse> loadNewProducts(
            @Field(ProductsConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(ProductsConstants.PARAM_PAGE) int page
    );

}
