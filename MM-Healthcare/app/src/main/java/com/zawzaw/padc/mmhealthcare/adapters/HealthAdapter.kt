package com.zawzaw.padc.mmhealthcare.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawzaw.padc.mmhealthcare.R
import com.zawzaw.padc.mmhealthcare.viewholders.HealthCareViewHolder

/**
 * Created by zawzaw on 13/07/2018.
 */

class HealthAdapter : RecyclerView.Adapter<HealthCareViewHolder>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthCareViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context);
        val view: View = layoutInflater.inflate(R.layout.view_holder_healthcare, parent, false);
        return HealthCareViewHolder(view);
    }

    override fun onBindViewHolder(holder: HealthCareViewHolder, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return 9;
    }

}