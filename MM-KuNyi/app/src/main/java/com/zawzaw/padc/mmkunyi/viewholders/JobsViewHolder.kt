package com.zawzaw.padc.mmkunyi.viewholders

import android.view.View
import com.zawzaw.padc.mmkunyi.data.vos.JobsVO

/**
 * Created by zawzaw on 01/08/2018.
 */

class JobsViewHolder(itemView: View) : BaseViewHolder<JobsVO>(itemView) {

    override fun bindData(data: JobsVO) {
        mData = data
    }

    override fun onClick(v: View?) {

    }

}
