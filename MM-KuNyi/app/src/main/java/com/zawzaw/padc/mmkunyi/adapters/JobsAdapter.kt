package com.zawzaw.padc.mmkunyi.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawzaw.padc.mmkunyi.R
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO
import com.zawzaw.padc.mmkunyi.viewholders.JobsViewHolder

/**
 * Created by zawzaw on 01/08/2018.
 */

class JobsAdapter : BaseAdapter<JobsVO, JobsViewHolder> {

    constructor() {
        jobsDataList = ArrayList()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.view_holder_jobs, parent, false)
        return JobsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobsDataList.size
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        holder.bindData(jobsDataList[position])
    }

    override fun setData(mList: MutableList<JobsVO>, context: RecyclerView) {
        jobsDataList = mList
        notifyDataSetChanged()

    }

    fun appendData(mList: List<JobsVO>, context: RecyclerView) {
        jobsDataList.addAll(mList)
        notifyDataSetChanged()

    }

}