package com.aramtory.stumeet.data.dto.res.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("isFirstLogin")
    val isFirstLogin: Boolean,
    @SerialName("refreshToken")
    val refreshToken: String,
)
