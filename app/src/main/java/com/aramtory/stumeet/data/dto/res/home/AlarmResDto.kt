package com.aramtory.stumeet.data.dto.res.home

import com.aramtory.stumeet.data.dto.common.PageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlarmResDto(
    @SerialName("notificationLogs")
    val notificationLogs: List<NotificationLog>?,
    @SerialName("pageInfo")
    val pageInfo: PageInfo
) {
    @Serializable
    data class NotificationLog(
        @SerialName("body")
        val body: String,
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("data")
        val `data`: Data?,
        @SerialName("id")
        val id: Int,
        @SerialName("imgUrl")
        val imgUrl: String,
        @SerialName("title")
        val title: String
    ) {
        @Serializable
        data class Data(
            @SerialName("type")
            val type: String
        )
    }
}