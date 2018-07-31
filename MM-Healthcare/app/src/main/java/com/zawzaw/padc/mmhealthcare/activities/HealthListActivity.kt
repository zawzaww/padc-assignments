package com.zawzaw.padc.mmhealthcare.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import org.greenrobot.eventbus.EventBus

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import com.zawzaw.padc.mmhealthcare.R
import com.zawzaw.padc.mmhealthcare.adapters.HealthAdapter
import com.zawzaw.padc.mmhealthcare.data.models.HealthModel
import com.zawzaw.padc.mmhealthcare.events.ApiErrorEvent
import com.zawzaw.padc.mmhealthcare.events.SuccessGetHealthEvent
import com.zawzaw.padc.mmhealthcare.viewpods.EmptyViewPod

import kotlinx.android.synthetic.main.activity_health_list.*

class HealthListActivity : BaseAcivity() {

    private var adapter: HealthAdapter? = null

    private var emptyViewPod: EmptyViewPod? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)
        setSupportActionBar(toolbar)

        emptyViewPod = vpEmpty as EmptyViewPod

        rv_healthcare.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)
        adapter = HealthAdapter()
        rv_healthcare.adapter = adapter

        swipeRefreshLayout.isRefreshing = true

        HealthModel.getObjInstance()!!.loadHealthCareInfo()

        swipeRefreshLayout.setOnRefreshListener {
            HealthModel.getObjInstance()!!.loadHealthCareInfo()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthList(successEvent: SuccessGetHealthEvent) {
        vpEmpty.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false

        adapter!!.setHealthList(successEvent.healthList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetHealthList(errorEvent: ApiErrorEvent) {
        vpEmpty.visibility = View.VISIBLE
        swipeRefreshLayout.isRefreshing = false

        emptyViewPod!!.setEmptyData(getString(R.string.empty_data_text), R.drawable.empty_data_placeholder)
    }

}
