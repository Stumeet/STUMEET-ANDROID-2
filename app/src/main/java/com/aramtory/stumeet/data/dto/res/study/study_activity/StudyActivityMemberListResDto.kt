package com.aramtory.stumeet.data.dto.res.study.study_activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyActivityMemberListResDto(
    @SerialName("participants")
    val participants: List<Participant>?
) {
    @Serializable
    data class Participant(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImageUrl")
        val profileImageUrl: String,
        @SerialName("status")
        val status: String
    )
}