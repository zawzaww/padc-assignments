package com.zawzaw.candkassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.data.vos.NewProductVO;
import com.zawzaw.candkassignment.delegates.NewProductDetailDelegate;
import com.zawzaw.candkassignment.utils.GlideApp;

public class ProductDetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_product_hero)
    ImageView ivProductHero;

    private NewProductDetailDelegate mDelegate;

    private NewProductVO mNewProduct;

    public ProductDetailViewHolder(View itemView, NewProductDetailDelegate detailDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = detailDelegate;
        itemView.setOnClickListener((v) -> mDelegate.onTapProduct(mNewProduct));

    }

    public void setData(NewProductVO newProduct) {
        mNewProduct = newProduct;
        GlideApp.with(ivProductHero)
                .load(mNewProduct.getProductImage())
                .placeholder(R.drawable.loader)
                .error(R.drawable.loader)
                .into(ivProductHero);
    }

}
