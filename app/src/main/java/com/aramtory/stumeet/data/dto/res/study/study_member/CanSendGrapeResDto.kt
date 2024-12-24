package com.aramtory.stumeet.data.dto.res.study.study_member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanSendGrapeResDto(
    @SerialName("canSendGrape")
    val canSendGrape: Boolean,
)