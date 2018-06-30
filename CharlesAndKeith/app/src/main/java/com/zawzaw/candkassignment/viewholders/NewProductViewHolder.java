package com.zawzaw.candkassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.data.vos.NewProductVO;
import com.zawzaw.candkassignment.delegates.NewProductDelegate;
import com.zawzaw.candkassignment.utils.GlideApp;

public class NewProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_image)
    ImageView ivItemImage;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    private NewProductDelegate mDelegate;
    private NewProductVO mNewProduct;

    public NewProductViewHolder(View itemView, NewProductDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = delegate;
        itemView.setOnClickListener((v) -> {
            mDelegate.onTapProduct(mNewProduct);
        });
    }

    public void setData(NewProductVO newProduct, boolean isTwoGrid) {

        mNewProduct = newProduct;
        tvItemName.setText(mNewProduct.getProductTitle());

        if (mNewProduct.getProductImage() == null) {

            ivItemImage.setImageResource(R.drawable.placeholder);

        } else {

            GlideApp.with(ivItemImage)
                    .load(mNewProduct.getProductImage())
                    .placeholder(R.drawable.loader)
                    .error(R.drawable.loader)
                    .into(ivItemImage);
        }
    }

}
