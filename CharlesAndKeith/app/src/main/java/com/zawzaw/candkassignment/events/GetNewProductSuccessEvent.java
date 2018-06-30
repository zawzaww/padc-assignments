package com.zawzaw.candkassignment.events;

import java.util.List;

import com.zawzaw.candkassignment.data.vos.NewProductVO;

public class GetNewProductSuccessEvent {

    private List<NewProductVO> mNewProducts;

    public GetNewProductSuccessEvent(List<NewProductVO> mNewProducts) {
        this.mNewProducts = mNewProducts;
    }

    public List<NewProductVO> getmNewProducts() {
        return mNewProducts;
    }

}
