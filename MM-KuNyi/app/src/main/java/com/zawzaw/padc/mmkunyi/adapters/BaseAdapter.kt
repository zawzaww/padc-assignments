package com.zawzaw.padc.mmkunyi.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.zawzaw.padc.mmkunyi.viewholders.BaseViewHolder

/**
 * Created by zawzaw on 03/08/2018.
 */

abstract class BaseAdapter<T, W>(context: Context) : RecyclerView.Adapter<BaseViewHolder<W>>() {

    protected var jobsData: MutableList<W>? = null
    protected var layoutInflater: LayoutInflater? = null

    val items: List<W>
        get() {
            val data = jobsData
            return data?: ArrayList()
        }

    init {
        jobsData = ArrayList()
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return jobsData!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<W>, position: Int) {
        holder.bindData(jobsData!![position])
    }

    fun setData(newData: MutableList<W>) {
        jobsData = newData
        notifyDataSetChanged()
    }

    fun appendData(newData: List<W>) {
        jobsData!!.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): W? {
        return if (position < jobsData!!.size - 1) jobsData!![position] else null
    }

}