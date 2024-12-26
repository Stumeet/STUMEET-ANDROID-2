package com.aramtory.stumeet.data.dto.res.study

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyListResDto(
    @SerialName("studySimpleResponses")
    val studySimpleResponses: List<StudySimpleResponse>?
) {
    @Serializable
    data class StudySimpleResponse(
        @SerialName("endDate")
        val endDate: String,
        @SerialName("field")
        val `field`: String,
        @SerialName("headcount")
        val headcount: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("name")
        val name: String,
        @SerialName("startDate")
        val startDate: String,
        @SerialName("tags")
        val tags: List<String>?
    )
}