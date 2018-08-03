package com.zawzaw.padc.mmkunyi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawzaw.padc.mmkunyi.R
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO
import com.zawzaw.padc.mmkunyi.viewholders.BaseViewHolder
import com.zawzaw.padc.mmkunyi.viewholders.JobsViewHolder

/**
 * Created by zawzaw on 01/08/2018.
 */

class JobsAdapter() : BaseAdapter<JobsViewHolder, JobsVO>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<JobsVO> {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.view_holder_jobs, parent, false)

        return JobsViewHolder(view)
    }

}