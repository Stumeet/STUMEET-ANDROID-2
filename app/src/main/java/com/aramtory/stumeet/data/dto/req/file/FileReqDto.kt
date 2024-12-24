package com.aramtory.stumeet.data.dto.req.file

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileReqDto(
    @SerialName("requests")
    val requests: List<Request>
) {
    @Serializable
    data class Request(
        @SerialName("fileName")
        val fileName: String,
        @SerialName("path")
        val path: String
    )
}