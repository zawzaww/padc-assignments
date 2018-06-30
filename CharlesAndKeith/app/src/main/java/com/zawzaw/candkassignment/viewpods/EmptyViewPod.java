package com.zawzaw.candkassignment.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.bumptech.glide.request.RequestOptions;
import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.utils.GlideApp;

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);

    }

    public void setEmptyData(String imageUrl, String emptyMsg) {

        GlideApp.with(getContext())
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(ivEmpty);

        tvEmpty.setText(emptyMsg);

    }

    public void setEmptyData(int emptyImageResource, String emptyMsg) {

        ivEmpty.setImageResource(emptyImageResource);
        tvEmpty.setText(emptyMsg);
    }

}
