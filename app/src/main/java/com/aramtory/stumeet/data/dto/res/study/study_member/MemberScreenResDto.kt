package com.aramtory.stumeet.data.dto.res.study.study_member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberScreenResDto(
    @SerialName("canSendGrape")
    val canSendGrape: Boolean,
    @SerialName("isAdmin")
    val isAdmin: Boolean,
    @SerialName("studyMemberDetailResponse")
    val studyMemberDetailResponse: StudyMemberDetailResponse?
) {
    @Serializable
    data class StudyMemberDetailResponse(
        @SerialName("achievement")
        val achievement: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("name")
        val name: String,
        @SerialName("profession")
        val profession: String,
        @SerialName("region")
        val region: String
    )
}