package com.zawzaw.padc.mmhealthcare.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.zawzaw.padc.mmhealthcare.R
import com.zawzaw.padc.mmhealthcare.adapters.HealthAdapter

import kotlinx.android.synthetic.main.activity_health_list.*

class HealthListActivity : BaseAcivity() {

    private var adapter: HealthAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_list)
        setSupportActionBar(toolbar)

        rv_healthcare.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)
        adapter = HealthAdapter()
        rv_healthcare.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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

}
