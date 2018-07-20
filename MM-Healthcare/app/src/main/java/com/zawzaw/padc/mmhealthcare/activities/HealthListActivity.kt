package com.zawzaw.padc.mmhealthcare.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import org.greenrobot.eventbus.EventBus

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import com.zawzaw.padc.mmhealthcare.R
import com.zawzaw.padc.mmhealthcare.adapters.HealthAdapter
import com.zawzaw.padc.mmhealthcare.data.models.HealthModel
import com.zawzaw.padc.mmhealthcare.events.ApiErrorEvent
import com.zawzaw.padc.mmhealthcare.events.SuccessGetHealthEvent

import kotlinx.android.synthetic.main.activity_health_list.*

class HealthListActivity : AppCompatActivity() {

    private var adapter: HealthAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)
        setSupportActionBar(toolbar)

        rv_healthcare.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)
        adapter = HealthAdapter()
        rv_healthcare.adapter = adapter

        HealthModel.getObjInstance()!!.loadHealthCareInfo()

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
        adapter!!.setHealthList(successEvent.healthList)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetHealthList(errorEvent: ApiErrorEvent) {
        Snackbar.make(rv_healthcare, errorEvent.errorMessage, Snackbar.LENGTH_INDEFINITE).show()
    }

}
