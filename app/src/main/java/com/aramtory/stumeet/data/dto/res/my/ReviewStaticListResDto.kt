package com.aramtory.stumeet.data.dto.res.my

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewStaticListResDto(
    @SerialName("tagCounts")
    val tagCounts: TagCounts?
) {
    @Serializable
    data class TagCounts(
        @SerialName("DILIGENCE")
        val DILIGENCE: Int?,
        @SerialName("TASK_COMMITMENT")
        val TASK_COMMITMENT: Int?,
        @SerialName("CONSISTENT_ATTENDANCE")
        val CONSISTENT_ATTENDANCE: Int?,
        @SerialName("BEST_LEADER")
        val BEST_LEADER: Int?,
        @SerialName("BEST_SUPPORTER")
        val BEST_SUPPORTER: Int?,
        @SerialName("FAST_RESPONSE")
        val FAST_RESPONSE: Int?,
        @SerialName("SLOW_RESPONSE")
        val SLOW_RESPONSE: Int?,
        @SerialName("DECISIVE_J")
        val DECISIVE_J: Int?,
        @SerialName("FLEXIBLE_P")
        val FLEXIBLE_P: Int?,
        @SerialName("MAX_RESPONSIBILITY")
        val MAX_RESPONSIBILITY: Int?,
        @SerialName("MOOD_MAKER")
        val MOOD_MAKER: Int?,
        @SerialName("EFFORT_TOP")
        val EFFORT_TOP: Int?,
        @SerialName("NEEDS_IMPROVEMENT")
        val NEEDS_IMPROVEMENT: Int?,
        @SerialName("NEAT_NOTES")
        val NEAT_NOTES: Int?,
        @SerialName("PINPOINT_TEACHER")
        val PINPOINT_TEACHER: Int?,
    )
}