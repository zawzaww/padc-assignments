package com.zawzaw.padc.mmkunyi.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.zawzaw.padc.mmkunyi.viewholders.BaseViewHolder
import com.zawzaw.padc.mmkunyi.viewholders.JobsViewHolder

/**
 * Created by zawzaw on 03/08/2018.
 */

abstract class BaseAdapter<VH, W> : RecyclerView.Adapter<BaseViewHolder<W>>() {

    protected var mList: List<W>? = null

    init {
        mList = ArrayList()
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<W>, position: Int) {
        holder.bindData(mList!![position])
    }

    fun setJobsList(jobsList: List<W>) {
        mList = jobsList
        notifyDataSetChanged()
    }

}