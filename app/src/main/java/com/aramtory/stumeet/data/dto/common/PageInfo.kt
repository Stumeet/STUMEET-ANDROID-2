package com.aramtory.stumeet.data.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("totalElements")
    val totalElements: Int,
    @SerialName("totalPages")
    val totalPages: Int
)