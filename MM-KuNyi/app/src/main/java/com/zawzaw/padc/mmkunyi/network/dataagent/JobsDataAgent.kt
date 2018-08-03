package com.zawzaw.padc.mmkunyi.network.dataagent

/**
 * Created by zawzaw on 02/08/2018.
 */

interface JobsDataAgent {

    fun loadJobsList(accessToken: String, page: Int)

}