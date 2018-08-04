package com.zawzaw.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 02/08/2018.
 */

class ApplicantVO {

    @SerializedName("appliedDate")
    val appliedDate: String = " "

    @SerializedName("canLowerOfferAmount")
    val canLowerOfferAmount: Boolean = false

    @SerializedName("seekerID")
    val seekerID: Int = 0

    @SerializedName("seekerName")
    val seekerName: String = " "

    @SerializedName("seekerProfilePicUrl")
    val seekerProfilePicUrl: String = " "

    @SerializedName("seekerSkill")
    val seekerSkill: List<SeekerSkillVO> = ArrayList()

    @SerializedName("whyShouldHire")
    val whyShouldHire: List<WhyShouldHireVO> = ArrayList()

}