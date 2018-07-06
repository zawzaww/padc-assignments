package com.zawzaw.padc.asartaLineapp.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.zawzaw.padc.asartaLineapp.R;
import com.zawzaw.padc.asartaLineapp.adapters.FoodAdapter;
import com.zawzaw.padc.asartaLineapp.data.models.WarDeeModel;
import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;
import com.zawzaw.padc.asartaLineapp.delegates.WarDeeDelegate;
import com.zawzaw.padc.asartaLineapp.events.ApiErrorEvent;
import com.zawzaw.padc.asartaLineapp.events.SuccessGetWarDeeEvent;

public class MainActivity extends BaseActivity implements WarDeeDelegate {

    private FoodAdapter mAdapter;

    @BindView(R.id.rv_asartaline_main)
    RecyclerView rvASarTaLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        mAdapter = new FoodAdapter(this);
        rvASarTaLine.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        rvASarTaLine.setAdapter(mAdapter);

        WarDeeModel.getObjInstance().loadWarDeeList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event) {
        mAdapter.setWarDeeList(event.getmWarDeeList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onApiError(ApiErrorEvent errorEvent) {
        Snackbar.make(rvASarTaLine, errorEvent.getmErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    @Override
    public void onTapWarDee(WarDeeVO warDee) {
        Log.d("onTapWarTeeItem", warDee.getWarDeeId());
    }

    @Override
    public void onSearch(String query) {
        Log.d("onSearch", query);
    }

    @Override
    public void onTapCategory(String category) {
        Log.d("onTapCategory", category);
    }

}