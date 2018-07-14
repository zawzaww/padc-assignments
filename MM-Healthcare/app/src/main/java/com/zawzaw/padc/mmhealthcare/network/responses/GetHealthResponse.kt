package com.zawzaw.padc.mmhealthcare.network.responses

import com.google.gson.annotations.SerializedName
import com.zawzaw.padc.mmhealthcare.data.vos.HealthCareVO

/**
 * Created by zawzaw on 14/07/2018.
 */

class GetHealthResponse {

    @SerializedName("code")
    val code: Int = 0

    @SerializedName("message")
    val message: String = " "

    @SerializedName("healthcare-info")
    val healthCareList: List<HealthCareVO>? = null

    fun isResponseOK(): Boolean {
        return code == 200 && healthCareList != null
    }

}
