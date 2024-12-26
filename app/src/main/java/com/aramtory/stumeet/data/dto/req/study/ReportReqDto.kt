package com.aramtory.stumeet.data.dto.req.study

import com.aramtory.stumeet.data.dto.common.enums.ReportReasonType
import com.aramtory.stumeet.data.dto.common.enums.ReportType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportReqDto(
    @SerialName("category")
    val category: ReportType,
    @SerialName("content")
    val content: String,
    @SerialName("reason")
    val reason: ReportReasonType,
    @SerialName("reportedId")
    val reportedId: Int,
    @SerialName("reporterId")
    val reporterId: Int
)