package com.aramtory.stumeet.data.dto.res.study.study_member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberListResDto(
    @SerialName("studyMembers")
    val studyMembers: List<StudyMember>?
) {
    @Serializable
    data class StudyMember(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("image")
        val image: String,
        @SerialName("isAdmin")
        val isAdmin: Boolean,
        @SerialName("profession")
        val profession: String,
        @SerialName("region")
        val region: String
    )
}