package com.aramtory.stumeet.data.dto.common.enums

enum class ReviewSortType(private val sort: String) {
    LATEST("최근순"),
    HIGHEST_RATING("별점 높은순"),
    LOWEST_RATING("별점 낮은순");

    fun getReviewSort(reviewSortType : ReviewSortType): String {
        return reviewSortType.sort
    }
}
