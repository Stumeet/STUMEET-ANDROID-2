package com.aramtory.stumeet.data.dto.req.study.study_activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyActivityReqDto(
    @SerialName("category")
    val category: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("isNotice")
    val isNotice: Boolean,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("link")
    val link: String? = null,
    @SerialName("participants")
    val participants: List<Long>
) {
}