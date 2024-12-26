package com.aramtory.stumeet.data.dto.res.study.study_activity

import com.aramtory.stumeet.data.dto.common.PageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyActivityDetailListResDto(
    @SerialName("items")
    val items: List<Item>?,
    @SerialName("pageInfo")
    val pageInfo: PageInfo?
) {
    @Serializable
    data class Item(
        @SerialName("id")
        val id: Int,
        @SerialName("author")
        val author: Author?,
        @SerialName("category")
        val category: String,
        @SerialName("content")
        val content: String,
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("location")
        val location: String?,
        @SerialName("startDate")
        val startDate: String?,
        @SerialName("endDate")
        val endDate: String?,
        @SerialName("title")
        val title: String
    ) {
        @Serializable
        data class Author(
            @SerialName("memberId")
            val memberId: Int,
            @SerialName("name")
            val name: String,
            @SerialName("profileImageUrl")
            val profileImageUrl: String
        )
    }
}