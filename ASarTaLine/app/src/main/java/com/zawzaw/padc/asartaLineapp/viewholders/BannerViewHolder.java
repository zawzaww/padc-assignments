package com.zawzaw.padc.asartaLineapp.viewholders;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;
import com.zawzaw.padc.asartaLineapp.delegates.WarDeeDelegate;

/**
 * Created by zawzaw on 05/07/2018.
 */

public class BannerViewHolder extends BaseViewHolder {

    private WarDeeDelegate mDelegate;

    @BindView(R.id.et_search_box) EditText etSearchBox;
    @BindView(R.id.iv_search_btn) ImageView ivSearchButton;

    public BannerViewHolder(View itemView, WarDeeDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mDelegate = delegate;

        etSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mDelegate.onSearch(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        ivSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onSearch(etSearchBox.getText().toString());
            }
        });

    }

    @Override
    public void bindData(WarDeeVO warDee) {
        super.bindData(warDee);
    }

}
