package com.zawzaw.candkassignment.events;

import java.util.List;

import com.zawzaw.candkassignment.data.vos.NewProductVO;

public class GetNewProductForceSuccessEvent {

    private List<NewProductVO> mNewProducts;

    public GetNewProductForceSuccessEvent(List<NewProductVO> mNewProducts) {
        this.mNewProducts = mNewProducts;
    }

    public List<NewProductVO> getmNewProducts() {
        return mNewProducts;
    }

}
