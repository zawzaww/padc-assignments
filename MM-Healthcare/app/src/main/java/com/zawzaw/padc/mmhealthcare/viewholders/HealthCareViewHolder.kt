package com.zawzaw.padc.mmhealthcare.viewholders

import android.view.View
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO

/**
 * Created by zawzaw on 13/07/2018.
 */

class HealthCareViewHolder(itemView: View) : BaseViewHolder<HealthCareVO>(itemView) {

    override fun binData(data: HealthCareVO) {
        mData = data

    }

    override fun onClick(v: View?) {

    }

}