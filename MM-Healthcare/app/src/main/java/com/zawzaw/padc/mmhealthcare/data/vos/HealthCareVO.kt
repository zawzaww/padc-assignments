package com.zawzaw.padc.mmhealthcare.data.vos

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 14/07/2018.
 */

class HealthCareVO {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("title")
    val title: String = " "

    @SerializedName("image")
    val image: String = " "

    @SerializedName("author")
    val author: AuthorVO? = null

    @SerializedName("short-description")
    val shortDescription: String = " "

    @SerializedName("published-date")
    val publishedDate: String = " "

    @SerializedName("complete-url")
    val competeUrl: String = " "

    @SerializedName("info-type")
    val infoType: String = " "

}
