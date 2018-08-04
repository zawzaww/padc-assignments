package com.zawzaw.padc.mmkunyi.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.zawzaw.padc.mmkunyi.R
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO
import com.zawzaw.padc.mmkunyi.viewholders.JobsViewHolder

/**
 * Created by zawzaw on 01/08/2018.
 */

class JobsAdapter(context: Context) : BaseAdapter<JobsViewHolder, JobsVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val jobsItemView: View = layoutInflater!!.inflate(R.layout.view_holder_jobs, parent, false)
        return JobsViewHolder(jobsItemView)
    }

}