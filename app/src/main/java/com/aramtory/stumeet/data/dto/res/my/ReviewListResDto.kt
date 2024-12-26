package com.aramtory.stumeet.data.dto.res.my

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ReviewListResDto : ArrayList<ReviewListResDto.ReviewListDtoItem>() {
    @Serializable
    data class ReviewListDtoItem(
        @SerialName("id")
        val id: Int,
        @SerialName("content")
        val content: String,
        @SerialName("rate")
        val rate: Int,
        @SerialName("tags")
        val tags: List<String>?,
        @SerialName("createdAt")
        val createdAt: String,
    )
}