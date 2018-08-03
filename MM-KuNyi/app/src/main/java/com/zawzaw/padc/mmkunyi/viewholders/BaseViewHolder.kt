package com.zawzaw.padc.mmkunyi.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zawzaw on 03/08/2018.
 */

abstract class BaseViewHolder<W>(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mData: W? = null

    init {
        itemView!!.setOnClickListener(this)
    }

    abstract fun bindData(data: W)

}