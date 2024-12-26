package com.aramtory.stumeet.data.dto.res.my

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyResDto(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profession")
    val profession: String,
    @SerialName("region")
    val region: String,
    @SerialName("experience")
    val experience: Double,
    @SerialName("tier")
    val tier: String
)