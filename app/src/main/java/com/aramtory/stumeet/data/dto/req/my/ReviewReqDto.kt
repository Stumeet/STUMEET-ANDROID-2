package com.aramtory.stumeet.data.dto.req.my

import com.aramtory.stumeet.data.enums.ReviewTagType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewReqDto(
    @SerialName("content")
    val content: String,
    @SerialName("rate")
    val rate: Int,
    @SerialName("reviewTags")
    val reviewTags: List<ReviewTagType>,
    @SerialName("revieweeId")
    val revieweeId: Int,
    @SerialName("studyId")
    val studyId: Int
)