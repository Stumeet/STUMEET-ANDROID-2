package com.aramtory.stumeet.data.dto.common.enums

import com.aramtory.stumeet.R

enum class StudySortType(private val stateResId: Int) {
    ACTIVE(R.string.enum_study_sort_active),
    FINISHED(R.string.enum_study_sort_finished);

    fun getStudySortType(studySortType: StudySortType): Int {
        return studySortType.stateResId
    }
}
