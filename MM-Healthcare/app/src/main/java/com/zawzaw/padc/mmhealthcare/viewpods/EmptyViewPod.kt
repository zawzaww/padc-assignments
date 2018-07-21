package com.zawzaw.padc.mmhealthcare.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.zawzaw.padc.mmhealthcare.R
import kotlinx.android.synthetic.main.view_pod_empty.view.*

/**
 * Created by zawzaw on 21/07/2018.
 */

class EmptyViewPod : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, styleAttr: Int) : super(context, attrs, styleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(errorMessage: String, errorImage: Int) {
        tv_empty_text.text = errorMessage
        iv_empty_image.setImageResource(R.drawable.empty_data_placeholder)
    }

}
