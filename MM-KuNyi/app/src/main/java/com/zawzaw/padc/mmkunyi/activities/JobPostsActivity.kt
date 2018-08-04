package com.zawzaw.padc.mmkunyi.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.zawzaw.padc.mmkunyi.R
import com.zawzaw.padc.mmkunyi.adapters.JobsAdapter
import com.zawzaw.padc.mmkunyi.data.models.JobsModel
import com.zawzaw.padc.mmkunyi.events.ApiErrorEvent
import com.zawzaw.padc.mmkunyi.events.ForceGetJobsEvent
import com.zawzaw.padc.mmkunyi.events.SuccessGetJobsEvent

import kotlinx.android.synthetic.main.activity_job_posts.*

class JobPostsActivity : BaseActivity() {

    private lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_posts)
        setSupportActionBar(toolbar)

        rvJobPosts.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)
        adapter = JobsAdapter()
        rvJobPosts.adapter = adapter

        JobsModel.getObjIntance()!!.loadJobsList()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
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
    fun onSuccessGetJobsList(successEvent: SuccessGetJobsEvent) {
        adapter.appendData(successEvent.jobsList, rvJobPosts as RecyclerView)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onForceGetJobsList(forceEvent: ForceGetJobsEvent) {
        adapter.setData(forceEvent.jobsList, rvJobPosts as RecyclerView)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetJobsList(errorEvent: ApiErrorEvent) {
        Snackbar.make(rvJobPosts, errorEvent.errorMessage, Snackbar.LENGTH_INDEFINITE).show()

    }

}
