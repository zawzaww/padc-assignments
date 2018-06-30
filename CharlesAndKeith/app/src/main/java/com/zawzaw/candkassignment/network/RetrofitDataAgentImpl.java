package com.zawzaw.candkassignment.network;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import com.zawzaw.candkassignment.events.ApiErrorEvent;
import com.zawzaw.candkassignment.events.GetNewProductForceSuccessEvent;
import com.zawzaw.candkassignment.events.GetNewProductSuccessEvent;
import com.zawzaw.candkassignment.network.responses.GetNewProductResponse;
import com.zawzaw.candkassignment.utils.ProductsConstants;

public class RetrofitDataAgentImpl implements ShopDataAgent {

    private static RetrofitDataAgentImpl objectReference;

    private ShopApi mTheApi;

    public static RetrofitDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new RetrofitDataAgentImpl();
        }
        return objectReference;
    }

    private RetrofitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductsConstants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(ShopApi.class);

    }

    @Override
    public void loadNewProducts(int page, String accessToken, boolean isForceRefresh) {

        Call<GetNewProductResponse> newProductResponseApiCall = mTheApi.loadNewProducts(accessToken, page);
        newProductResponseApiCall.enqueue(new Callback<GetNewProductResponse>() {
            @Override
            public void onResponse(Call<GetNewProductResponse> call, Response<GetNewProductResponse> response) {
                GetNewProductResponse newProductResponse = response.body();
                if (newProductResponse != null && newProductResponse.isResponseOk()) {

                    if (isForceRefresh) {

                        GetNewProductForceSuccessEvent successForceEvent = new GetNewProductForceSuccessEvent(newProductResponse.getNewProduct());
                        EventBus.getDefault().post(successForceEvent);

                    } else {
                        GetNewProductSuccessEvent successEvent = new GetNewProductSuccessEvent(newProductResponse.getNewProduct());
                        EventBus.getDefault().post(successEvent);
                    }
                } else {
                    if (newProductResponse == null) {

                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in response!");
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(newProductResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);

                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductResponse> call, Throwable t) {
                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });

    }
}
