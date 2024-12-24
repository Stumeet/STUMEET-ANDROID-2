package com.aramtory.stumeet.data.dto.res.study.study_activity

import com.aramtory.stumeet.data.dto.common.PageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyActivityBriefListResDto(
    @SerialName("items")
    val items: List<Item>?,
    @SerialName("pageInfo")
    val pageInfo: PageInfo
) {
    @Serializable
    data class Item(
        @SerialName("id")
        val id: Int,
        @SerialName("category")
        val category: String,
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("title")
        val title: String,
        @SerialName("location")
        val location: String?,
        @SerialName("startDate")
        val startDate: String?,
        @SerialName("endDate")
        val endDate: String?,
        @SerialName("status")
        val status: String?,
    )
}