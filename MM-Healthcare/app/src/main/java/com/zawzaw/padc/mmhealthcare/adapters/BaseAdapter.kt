package com.zawzaw.padc.mmhealthcare.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.zawzaw.padc.mmhealthcare.viewholders.BaseViewHolder

/**
 * Created by zawzaw on 14/07/2018.
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
        holder.binData(mList!![position])
    }

    fun setHealthList(healthCareList: List<W>) {
        mList = healthCareList
        notifyDataSetChanged()
    }

}
