package com.zawzaw.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 02/08/2018.
 */

class JobDurationVO {

    @SerializedName("jobEndDate")
    val jobEndDate: String = " "

    @SerializedName("jobStartDate")
    val jobStartDate: String = " "

    @SerializedName("totalWorkingDays")
    val totalWorkingDays: Int = 0

    @SerializedName("workingDaysPerWeek")
    val workingDaysPerWeek: Int = 0

    @SerializedName("workingHoursPerDay")
    val workingHoursPerDay: Int = 0

}