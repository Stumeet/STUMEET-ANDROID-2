package com.aramtory.stumeet.data.dto.res.file

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileResDto(
    @SerialName("presignedUrls")
    val presignedUrls: List<PresignedUrl?>?
) {
    @Serializable
    data class PresignedUrl(
        @SerialName("url")
        val url: String?
    )
}