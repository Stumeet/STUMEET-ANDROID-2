package com.aramtory.stumeet.data.dto.res.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PartListResDto(
    @SerialName("professions")
    val professions: List<Profession>?,
) {
    @Serializable
    data class Profession(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
    )
}