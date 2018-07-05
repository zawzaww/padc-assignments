package com.zawzaw.padc.asartaLineapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.viewholders.BannerViewHolder;
import com.zawzaw.padc.asartaLineapp.viewholders.BaseViewHolder;

/**
 * Created by zawzaw on 05/07/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int BANNER_VIEW = 1;
    private final int FOOD_OFFER_VIEW = 2;
    private final int RESTURANT_VIEW = 3;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case BANNER_VIEW :
                View view = inflater.inflate(R.layout.view_holder_banner, parent, false);
                return new BannerViewHolder(view);

            case FOOD_OFFER_VIEW :
                break;

            case  RESTURANT_VIEW :
                return null;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_VIEW;

            case 1:
                return FOOD_OFFER_VIEW;

            default:
                return RESTURANT_VIEW;
        }
    }

}
