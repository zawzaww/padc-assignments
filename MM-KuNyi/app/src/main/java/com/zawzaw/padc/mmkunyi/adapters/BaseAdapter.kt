package com.zawzaw.padc.mmkunyi.adapters

import android.support.v7.widget.RecyclerView
import com.zawzaw.padc.mmkunyi.viewholders.BaseViewHolder

/**
 * Created by zawzaw on 03/08/2018.
 */

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH> {

    protected var jobsDataList: MutableList<T> = ArrayList()

    constructor() : super()

    abstract fun setData(mList: MutableList<T>, context: RecyclerView)

}