package com.zawzaw.padc.asartaLineapp.viewholders;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;
import com.zawzaw.padc.asartaLineapp.delegates.WarDeeDelegate;
import com.zawzaw.padc.asartaLineapp.utils.GlideApp;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class OffersFoodViewHolder extends BaseViewHolder {

    private WarDeeDelegate mDelegate;

    private WarDeeVO mWarDee;

    @BindView(R.id.iv_offer_food)
    ImageView ivOfferFood;

    @BindView(R.id.tv_food_title)
    TextView tvFoodtitle;

    @BindView(R.id.tv_food_subtitle)
    TextView tvFoodSubtitle;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.cv_cover)
    CardView cvCover;

    public OffersFoodViewHolder(View itemView, WarDeeDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mDelegate = delegate;

        cvCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapWarDee(mWarDee);
            }
        });

    }

    @Override
    public void bindData(WarDeeVO warDee) {
        super.bindData(warDee);
        mWarDee = warDee;

        GlideApp.with(ivOfferFood.getContext())
                .load(mWarDee.getImages().get(0))
                .centerCrop()
                .into(ivOfferFood);

        tvFoodtitle.setText(mWarDee.getName());

        tvFoodSubtitle.setText(mWarDee.getGeneralTaste().get(0).getTaste());

        tvPrice.setText(tvPrice.getContext().getString(R.string.format_price,
                mWarDee.getPriceRangeMin(), mWarDee.getPriceRangeMax()));

    }

}
