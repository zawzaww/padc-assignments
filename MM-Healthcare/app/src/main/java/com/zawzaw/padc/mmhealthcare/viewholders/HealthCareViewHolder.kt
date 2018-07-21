package com.zawzaw.padc.mmhealthcare.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO

import kotlinx.android.synthetic.main.view_holder_healthcare.view.*

/**
 * Created by zawzaw on 13/07/2018.
 */

class HealthCareViewHolder(itemView: View) : BaseViewHolder<HealthCareVO>(itemView) {

    override fun binData(data: HealthCareVO) {
        mData = data

        // Health article title
        itemView.tv_title.text = mData!!.title

        // Helath short description
        itemView.tv_short_description.text = mData!!.shortDescription

        // Hero image
        Glide.with(itemView.iv_hero_img)
                .load(mData!!.image)
                .into(itemView.iv_hero_img)

        // Information type
        itemView.tv_info_type.text = mData!!.infoType

        // Author profile picture
        Glide.with(itemView.iv_author_img)
                .load(mData!!.author!!.authorPicture)
                .apply(RequestOptions
                        // Change to circle image icon design.
                        .circleCropTransform())
                .into(itemView.iv_author_img)

        // Author name
        itemView.tv_author_name.text = mData!!.author!!.authorName

        // Published date
        itemView.tv_published_date.text = mData!!.publishedDate

    }

    override fun onClick(v: View?) {

    }

}
