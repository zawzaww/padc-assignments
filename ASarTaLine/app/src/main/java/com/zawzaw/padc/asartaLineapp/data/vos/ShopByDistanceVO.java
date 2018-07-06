package com.zawzaw.padc.asartaLineapp.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class ShopByDistanceVO {

    @SerializedName("shopByDistanceId")
    private String shopByDistanceId;

    @SerializedName("mealShop")
    private MealShopVO mealShop;

    @SerializedName("distanceInFeet")
    private double distanceInFeet;

    public String getShopByDistanceId() {
        return shopByDistanceId;
    }

    public MealShopVO getMealShop() {
        return mealShop;
    }

    public double getDistanceInFeet() {
        return distanceInFeet;
    }

}
