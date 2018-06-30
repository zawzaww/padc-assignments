package com.zawzaw.candkassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.candkassignment.R;

public class ColourProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_color_name)
    TextView tvColorName;

    @BindView(R.id.iv_color_item)
    ImageView ivColorItems;

    public ColourProductViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void setData(String colorName, int colorCode) {

        tvColorName.setText(colorName);
        ivColorItems.setBackgroundColor(colorCode);
    }
}
