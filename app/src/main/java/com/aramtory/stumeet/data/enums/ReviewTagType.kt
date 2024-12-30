package com.aramtory.stumeet.data.enums

import com.aramtory.stumeet.R

enum class ReviewTagType(private val tagResId: Int) {
    DILIGENCE(R.string.enum_review_tag_diligence),
    TASK_COMMITMENT(R.string.enum_review_tag_task_commitment),
    CONSISTENT_ATTENDANCE(R.string.enum_review_tag_consistent_attendance),
    BEST_LEADER(R.string.enum_review_tag_best_leader),
    BEST_SUPPORTER(R.string.enum_review_tag_best_supporter),
    FAST_RESPONSE(R.string.enum_review_tag_fast_response),
    SLOW_RESPONSE(R.string.enum_review_tag_slow_response),
    DECISIVE_J(R.string.enum_review_tag_j),
    FLEXIBLE_P(R.string.enum_review_tag_p),
    MAX_RESPONSIBILITY(R.string.enum_review_tag_max_responsibility),
    MOOD_MAKER(R.string.enum_review_tag_mood_maker),
    EFFORT_TOP(R.string.enum_review_tag_effot_top),
    NEEDS_IMPROVEMENT(R.string.enum_review_tag_needs_improvement),
    NEAT_NOTES(R.string.enum_review_tag_neat_notes),
    PINPOINT_TEACHER(R.string.enum_review_tag_pinpoint_teacher);


    fun getReviewTagSort(reviewTagType: ReviewTagType): Int {
        return reviewTagType.tagResId
    }
}
