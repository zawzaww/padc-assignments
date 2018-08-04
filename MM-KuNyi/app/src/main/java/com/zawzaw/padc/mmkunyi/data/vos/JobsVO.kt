package com.zawzaw.padc.mmkunyi.data.vos

import com.google.gson.annotations.SerializedName

/**
 * Created by zawzaw on 02/08/2018.
 */

class JobsVO(@SerializedName("applicant") val applicant: List<ApplicantVO> = ArrayList(),
             @SerializedName("availablePostCount") val availablePostCount: Int = 0,
             @SerializedName("email") val email: String = " ",
             @SerializedName("fullDesc") val fullDesc: String = " ",
             @SerializedName("genderForJob") val genderForJob: Int = 0,
             @SerializedName("images") val images: List<String> = ArrayList(),
             @SerializedName("importantNotes") val importantNotes: List<String> = ArrayList(),
             @SerializedName("interested") val interested: List<InterestedVO> = ArrayList(),
             @SerializedName("isActive") val isActive: Boolean = false,
             @SerializedName("jobDuration") val jobDuration: JobDurationVO? = null,
             @SerializedName("jobPostId") var jobPostId: Int = 0,
             @SerializedName("jobTags") val jobTags: List<JobTagsVO> = ArrayList(),
             @SerializedName("location") val location: String = " ",
             @SerializedName("makeMoneyRating") val makeMoneyRating: Int = 0,
             @SerializedName("offerAmount") val offerAmount: OfferAmountVO? = null,
             @SerializedName("phoneNo") val phoneNo: String = " ",
             @SerializedName("postClosedDate") val postClosedDate: String = " ",
             @SerializedName("postedDate") val postedDate: String = " ",
             @SerializedName("relevant") val relevant: List<RelevantVO> = ArrayList(),
             @SerializedName("requiredSkill") val requiredSkill: List<RequiredSkillVO> = ArrayList(),
             @SerializedName("shortDesc") val shortDesc: String = " ",
             @SerializedName("viewed") val viewed: List<ViewedVO> = ArrayList()) {

//    fun getJobPostId(): Int {
//        return jobPostId
//    }

}