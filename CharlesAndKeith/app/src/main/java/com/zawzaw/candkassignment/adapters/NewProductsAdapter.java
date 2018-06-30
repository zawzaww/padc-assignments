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
import com.zawzaw.candkassignment.delegates.NewProductDelegate;
import com.zawzaw.candkassignment.viewholders.NewProductViewHolder;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductViewHolder> {

    private NewProductDelegate mDelegate;

    private List<NewProductVO> mProductList;

    private boolean isTwoGrid;

    public NewProductsAdapter(NewProductDelegate delegate) {
        mDelegate = delegate;
        isTwoGrid = true;
        mProductList = new ArrayList<>();
    }

    public void setLayoutManager(boolean isTwoGrid) {
        this.isTwoGrid = isTwoGrid;
    }

    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_item, parent, false);
        return new NewProductViewHolder(view, mDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductViewHolder holder, int position) {

        holder.setData(mProductList.get(position), isTwoGrid);

    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void setNewProductList(List<NewProductVO> newProductList) {
        mProductList = newProductList;
        notifyDataSetChanged();
    }

    public void appendNewProductList(List<NewProductVO> newProductList) {
        mProductList.addAll(newProductList);
        notifyDataSetChanged();
    }
}
