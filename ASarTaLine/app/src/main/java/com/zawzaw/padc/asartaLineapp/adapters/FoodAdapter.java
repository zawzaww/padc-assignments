package com.zawzaw.padc.asartaLineapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.viewholders.BannerViewHolder;
import com.zawzaw.padc.asartaLineapp.viewholders.BaseViewHolder;
import com.zawzaw.padc.asartaLineapp.viewholders.FoodCatViewHolder;

/**
 * Created by zawzaw on 05/07/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int BANNER_VIEW = 1;
    private final int FOOD_CAT_VIEW = 2;
    private final int RESTURANT_VIEW = 3;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case BANNER_VIEW:
                View bannerView = inflater.inflate(R.layout.view_holder_banner, parent, false);
                return new BannerViewHolder(bannerView);

            case FOOD_CAT_VIEW:
                View foodCatView = inflater.inflate(R.layout.view_holder_foodcat, parent, false);
                return new FoodCatViewHolder(foodCatView);

            case RESTURANT_VIEW:
                return null;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_VIEW;

            case 1:
                return FOOD_CAT_VIEW;

            default:
                return RESTURANT_VIEW;
        }
    }
}
