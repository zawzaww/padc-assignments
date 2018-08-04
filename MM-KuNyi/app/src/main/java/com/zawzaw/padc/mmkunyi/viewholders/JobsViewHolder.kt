package com.zawzaw.padc.mmkunyi.viewholders

import android.view.View
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO

import kotlinx.android.synthetic.main.view_holder_jobs.view.*

/**
 * Created by zawzaw on 01/08/2018.
 */

class JobsViewHolder(itemView: View) : BaseViewHolder<JobsVO>(itemView) {

    override fun bindData(data: JobsVO) {
        mData = data

        // Job Title
        itemView.tvJobtTitle.text = mData!!.shortDesc

        // Job Full Description
        itemView.tvJobDescription.text = mData!!.fullDesc

    }

    override fun onClick(v: View?) {

    }

}
