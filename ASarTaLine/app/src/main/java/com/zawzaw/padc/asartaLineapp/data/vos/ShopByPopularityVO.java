package com.zawzaw.padc.asartaLineapp.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class ShopByPopularityVO {

    @SerializedName("shopByPopularityId")
    private String shopByPopularityId;

    @SerializedName("mealShop")
    private MealShopVO mealShop;

    public String getShopByPopularityId() {
        return shopByPopularityId;
    }

    public MealShopVO getMealShop() {
        return mealShop;
    }

}
