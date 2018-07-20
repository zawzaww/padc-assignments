package com.zawzaw.padc.mmhealthcare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawzaw.padc.mmhealthcare.R
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO
import com.zawzaw.padc.mmhealthcare.viewholders.BaseViewHolder
import com.zawzaw.padc.mmhealthcare.viewholders.HealthCareViewHolder

/**
 * Created by zawzaw on 13/07/2018.
 */

class HealthAdapter : BaseAdapter<HealthCareViewHolder, HealthCareVO>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HealthCareVO> {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.view_holder_healthcare, parent, false)
        return HealthCareViewHolder(view)
    }

}
