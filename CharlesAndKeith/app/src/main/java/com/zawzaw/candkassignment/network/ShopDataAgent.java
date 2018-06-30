package com.zawzaw.candkassignment.network;

public interface ShopDataAgent {

    void loadNewProducts(int page, String accessToken, boolean isForceRefresh);

}
