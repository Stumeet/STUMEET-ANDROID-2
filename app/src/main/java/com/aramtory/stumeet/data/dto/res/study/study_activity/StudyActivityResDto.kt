package com.aramtory.stumeet.data.dto.res.study.study_activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyActivityResDto(
    @SerialName("id")
    val id: Int,
    @SerialName("category")
    val category: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("imageUrl")
    val imageUrl: List<ImageUrl>?,
    @SerialName("author")
    val author: Author,
    @SerialName("participants")
    val participants: List<Participant>?,
    @SerialName("status")
    val status: String?,
    @SerialName("startDate")
    val startDate: String?,
    @SerialName("endDate")
    val endDate: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("isAuthor")
    val isAuthor: Boolean,
    @SerialName("isAdmin")
    val isAdmin: Boolean,
) {
    @Serializable
    data class Author(
        @SerialName("memberId")
        val memberId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImageUrl")
        val profileImageUrl: String,
    )

    @Serializable
    data class ImageUrl(
        @SerialName("id")
        val id: Int,
        @SerialName("imageUrl")
        val imageUrl: String,
    )

    @Serializable
    data class Participant(
        @SerialName("memberId")
        val memberId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImageUrl")
        val profileImageUrl: String,
    )
}
