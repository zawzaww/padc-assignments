package com.zawzaw.candkassignment.viewpods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.adapters.ItemColourAdapter;
import com.zawzaw.candkassignment.delegates.RightDrawerDelegate;

public class RightDrawerViewPod extends RelativeLayout {

    @BindView(R.id.rv_colours)
    RecyclerView rvColours;

    @BindView(R.id.btn_colour_two)
    ImageView btnDrawerClose;

    private RightDrawerDelegate mDelegate;
    private HashMap<String, Integer> mColorMap;
    private ItemColourAdapter adapter;

    public RightDrawerViewPod(Context context) {
        super(context);
    }

    public void setDelegate(RightDrawerDelegate delegate) {
        this.mDelegate = delegate;
    }

    public RightDrawerViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightDrawerViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
        btnDrawerClose.setOnClickListener((v) -> mDelegate.onDrawerClose());
        adapter = new ItemColourAdapter();
        rvColours.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvColours.setAdapter(adapter);
        adapter.setColourMap(generateDummyColorMap());

    }

    @SuppressLint("NewApi")
    private HashMap<String, Integer> generateDummyColorMap() {
        mColorMap = new HashMap<>();
        mColorMap.put("RED", getContext().getColor(R.color.dummyColorRed));
        mColorMap.put("BLUE", getContext().getColor(R.color.dummyColorBlue));
        mColorMap.put("YELLOW", getContext().getColor(R.color.dummyColorYellow));
        return mColorMap;
    }

}
