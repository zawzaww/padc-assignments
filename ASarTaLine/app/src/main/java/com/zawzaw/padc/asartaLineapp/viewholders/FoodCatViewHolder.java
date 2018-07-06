package com.zawzaw.padc.asartaLineapp.viewholders;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;
import com.zawzaw.padc.asartaLineapp.delegates.WarDeeDelegate;
import com.zawzaw.padc.asartaLineapp.utils.AppConstants;

/**
 * Created by zawzaw on 05/07/2018.
 */

public class FoodCatViewHolder extends BaseViewHolder {

    @BindView(R.id.iv_chinese_food)
    ImageView ivChinesFood;

    @BindView(R.id.iv_pizza)
    ImageView ivPizza;

    @BindView(R.id.iv_burger)
    ImageView ivBurger;

    @BindView(R.id.iv_donut)
    ImageView ivDonut;

    private WarDeeDelegate mDelegate;

    public FoodCatViewHolder(View itemView, WarDeeDelegate delegate) {
        super(itemView);
        mDelegate = delegate;
        ButterKnife.bind(this, itemView);

        ivChinesFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapCategory(AppConstants.CHINESE_FOOD);
            }
        });

        ivPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapCategory(AppConstants.PIZZA_FOOD);
            }
        });

        ivBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapCategory(AppConstants.BURGER_FOOD);
            }
        });

        ivDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapCategory(AppConstants.DONUT_FOOD);
            }
        });
    }

    @Override
    public void bindData(WarDeeVO warDee) {
        super.bindData(warDee);
    }

}
