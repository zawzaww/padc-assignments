package com.zawzaw.padc.mmhealthcare.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO

import kotlinx.android.synthetic.main.view_holder_healthcare.view.*

/**
 * Created by zawzaw on 13/07/2018.
 */

class HealthCareViewHolder(itemView: View) : BaseViewHolder<HealthCareVO>(itemView) {

    override fun binData(data: HealthCareVO) {
        mData = data

        // Health title
        itemView.tv_title.text = mData!!.title

        // Helath short description
        itemView.tv_short_description.text = mData!!.shortDescription
        
        Glide.with(itemView.iv_hero_img)
                .load(mData!!.image)
                .into(itemView.iv_hero_img)

    }

    override fun onClick(v: View?) {

    }

}