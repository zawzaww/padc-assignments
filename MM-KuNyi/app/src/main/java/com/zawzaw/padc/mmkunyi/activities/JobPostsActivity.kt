package com.zawzaw.padc.mmkunyi.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import com.zawzaw.padc.mmkunyi.R
import com.zawzaw.padc.mmkunyi.adapters.JobsAdapter
import com.zawzaw.padc.mmkunyi.data.models.JobsModel
import com.zawzaw.padc.mmkunyi.events.ApiErrorEvent
import com.zawzaw.padc.mmkunyi.events.SuccessGetJobsEvent

import kotlinx.android.synthetic.main.activity_job_posts.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobPostsActivity : BaseActivity() {

    private lateinit var jobsAdapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_posts)
        setSupportActionBar(toolbar)

        rvJobPosts.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)
        jobsAdapter = JobsAdapter()
        rvJobPosts.adapter = jobsAdapter

        JobsModel.getObjIntance()!!.loadJobs()

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
    fun onSuccessGetJobsList(successEvent: SuccessGetJobsEvent) {
        jobsAdapter.setJobsList(successEvent.jobsList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetJobsList(errorEvent: ApiErrorEvent) {
        Snackbar.make(rvJobPosts, "No data available now!", Snackbar.LENGTH_INDEFINITE).show()
    }

}
