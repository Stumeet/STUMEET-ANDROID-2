package com.aramtory.stumeet.data.dto.res.study

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyDetailResDto(
    @SerialName("endDate")
    val endDate: String,
    @SerialName("field")
    val field: String,
    @SerialName("headcount")
    val headcount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("intro")
    val intro: String?,
    @SerialName("isDeleted")
    val isDeleted: Boolean,
    @SerialName("isFinished")
    val isFinished: Boolean,
    @SerialName("meetingRepetitionDates")
    val meetingRepetitionDates: List<String?>?,
    @SerialName("meetingRepetitionType")
    val meetingRepetitionType: String,
    @SerialName("meetingTime")
    val meetingTime: String,
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("rule")
    val rule: String?,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("tags")
    val tags: List<String?>?
)
