package com.aramtory.stumeet.data.enums

import com.aramtory.stumeet.R

enum class ReviewSortType(private val sortResId: Int) {
    LATEST(R.string.enum_review_sort_latest),
    HIGHEST_RATING(R.string.enum_review_sort_highest_rating),
    LOWEST_RATING(R.string.enum_review_sort_lowest_rating);

    fun getReviewSort(reviewSortType : ReviewSortType): Int {
        return reviewSortType.sortResId
    }
}
