package com.zawzaw.candkassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.zawzaw.candkassignment.R;
import com.zawzaw.candkassignment.adapters.NewProductsAdapter;
import com.zawzaw.candkassignment.data.models.NewProductsModel;
import com.zawzaw.candkassignment.data.vos.NewProductVO;
import com.zawzaw.candkassignment.delegates.NewProductDelegate;
import com.zawzaw.candkassignment.events.ApiErrorEvent;
import com.zawzaw.candkassignment.events.GetNewProductForceSuccessEvent;
import com.zawzaw.candkassignment.events.GetNewProductSuccessEvent;
import com.zawzaw.candkassignment.utils.ProductsConstants;
import com.zawzaw.candkassignment.viewpods.EmptyViewPod;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewProductDelegate {

    @BindView(R.id.rv_new_items) RecyclerView rvNewItems;
    @BindView(R.id.iv_single_view) ImageView ivSingleView;
    @BindView(R.id.iv_dual_view) ImageView ivDualView;
    @BindView(R.id.v_single_highlighter) View vSingleViewHighlighter;
    @BindView(R.id.v_dual_highlighter) View vDualViewHighlighter;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_item_count) TextView tvItemCount;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.vp_empty) EmptyViewPod vpEmpty;

    private NewProductsAdapter adapter;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvItemCount.setText("20 Items");

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_opener);

        rvNewItems.setLayoutManager(new GridLayoutManager(this, 2));
        rvNewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isEndReached) {
                    isEndReached = true;
                    NewProductsModel.getObjectReference().loadNewProducts();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleCount = recyclerView.getLayoutManager().getChildCount();
                int totalCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItemCount = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleCount + pastVisibleItemCount) < totalCount) {
                    isEndReached = false;
                    NewProductsModel.getObjectReference().loadNewProducts();
                }

            }
        });
        adapter = new NewProductsAdapter(this);
        rvNewItems.setAdapter(adapter);

        NewProductsModel.getObjectReference().loadNewProducts();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> NewProductsModel.getObjectReference().forceRefreshNewProducts());

        ivDualView.setOnClickListener(v -> {
                    adapter.setLayoutManager(true);
                    rvNewItems.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
                    vDualViewHighlighter.setVisibility(View.VISIBLE);
                    vSingleViewHighlighter.setVisibility(View.GONE);
                }
        );

        ivSingleView.setOnClickListener(v -> {
            adapter.setLayoutManager(false);
            rvNewItems.setLayoutManager(new GridLayoutManager(v.getContext(), 1));
            vSingleViewHighlighter.setVisibility(View.VISIBLE);
            vDualViewHighlighter.setVisibility(View.GONE);
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTapProduct(NewProductVO newProduct) {
        Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
        intent.putExtra(ProductsConstants.NEW_PRODUCT_ID, newProduct.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(GetNewProductSuccessEvent event) {
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts: " + event.getmNewProducts().size());
        swipeRefreshLayout.setRefreshing(false);
        adapter.appendNewProductList(event.getmNewProducts());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onerrorGetNewProducts(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        if (!event.getErrorMessage().toLowerCase().equals("success")) {
            swipeRefreshLayout.setVisibility(View.GONE);
            vpEmpty.setVisibility(View.VISIBLE);
            vpEmpty.setEmptyData(R.drawable.empty_data_placeholder, "Sorry, no product items available!");
            tvItemCount.setText("");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceEvent(GetNewProductForceSuccessEvent event) {
        Log.d("onSuccessForceEvent", "onSuccessForceEvent: " + event.getmNewProducts().size());
        swipeRefreshLayout.setRefreshing(false);
        adapter.setNewProductList(event.getmNewProducts());
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
}
