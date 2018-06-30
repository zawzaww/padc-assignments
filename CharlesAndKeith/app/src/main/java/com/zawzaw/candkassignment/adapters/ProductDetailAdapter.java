package com.zawzaw.candkassignment.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.data.vos.NewProductVO;
import com.zawzaw.candkassignment.delegates.NewProductDetailDelegate;
import com.zawzaw.candkassignment.viewholders.ProductDetailViewHolder;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailViewHolder> {

    private NewProductDetailDelegate mDelegate;

    private List<NewProductVO> newProductList;

    public ProductDetailAdapter(NewProductDetailDelegate delegate) {
        mDelegate = delegate;
        newProductList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_product, parent, false);
        return new ProductDetailViewHolder(view, mDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailViewHolder holder, int position) {
        holder.setData(newProductList.get(position));
    }


    @Override
    public int getItemCount() {
        return newProductList.size();
    }

    public void setNewProductList(List<NewProductVO> newProductList) {
        this.newProductList = newProductList;
        notifyDataSetChanged();
    }
}
