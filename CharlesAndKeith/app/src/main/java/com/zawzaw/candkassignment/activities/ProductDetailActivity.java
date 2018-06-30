package com.zawzaw.candkassignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.adapters.ProductDetailAdapter;
import com.zawzaw.candkassignment.data.models.NewProductsModel;
import com.zawzaw.candkassignment.data.vos.NewProductVO;
import com.zawzaw.candkassignment.delegates.NewProductDetailDelegate;
import com.zawzaw.candkassignment.delegates.RightDrawerDelegate;
import com.zawzaw.candkassignment.utils.ProductsConstants;
import com.zawzaw.candkassignment.viewpods.EmptyViewPod;
import com.zawzaw.candkassignment.viewpods.RightDrawerViewPod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends BaseActivity
        implements RightDrawerDelegate, NewProductDetailDelegate {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView tvTitle;
    @BindView(R.id.btnColours) ImageView btnColours;
    @BindView(R.id.rv_details) RecyclerView rvDetails;
    @BindView(R.id.vp_colours) RightDrawerViewPod vpColoursDrawer;
    @BindView(R.id.tv_product_name) TextView tvProductName;
    @BindView(R.id.vp_empty) EmptyViewPod vpEmpty;
    @BindView(R.id.rl_container) RelativeLayout rlContainer;
    @BindView(R.id.btnInfo) Button btnInfo;

    private ProductDetailAdapter adapter;
    private NewProductVO newProductVO;
    private List<NewProductVO> mProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int productId = getIntent().getIntExtra(ProductsConstants.NEW_PRODUCT_ID, 0);
        newProductVO = NewProductsModel.getObjectReference().getProductById(productId);
        mProductList = new ArrayList<>();
        if (newProductVO == null){

            vpEmpty.setVisibility(View.VISIBLE);
            vpEmpty.setEmptyData("https://cdn1.iconfinder.com/data/icons/hawcons/32/698395-icon-131-cloud-error-512.png",
                    "Sorry, no product item available!");
            rlContainer.setVisibility(View.GONE);

        }else{
            vpEmpty.setVisibility(View.GONE);
            rlContainer.setVisibility(View.VISIBLE);
            bindData();
        }

        adapter = new ProductDetailAdapter(this);
        rvDetails.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvDetails.setAdapter(adapter);
        adapter.setNewProductList(mProductList);

        btnColours.setOnClickListener((v) -> {
            vpColoursDrawer.setVisibility(View.VISIBLE);
            btnInfo.setVisibility(View.GONE);
        });

        vpColoursDrawer.setDelegate(this);

    }

    private void bindData(){
        for (int i = 0; i < 10; i++) {
            mProductList.add(newProductVO);
        }
        tvProductName.setText(newProductVO.getProductTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onDrawerClose() {
        vpColoursDrawer.setVisibility(View.GONE);
        btnInfo.setVisibility(View.VISIBLE);
    }


    @Override
    public void onTapProduct(NewProductVO newProduct) {

    }

}
