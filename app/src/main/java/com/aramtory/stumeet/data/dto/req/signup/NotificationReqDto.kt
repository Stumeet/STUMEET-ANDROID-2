package com.aramtory.stumeet.data.dto.req.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationReqDto(
    @SerialName("deviceId")
    val deviceId: String,
    @SerialName("notificationToken")
    val notificationToken: String?
)