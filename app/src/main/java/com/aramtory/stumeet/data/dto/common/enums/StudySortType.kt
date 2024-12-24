package com.aramtory.stumeet.data.dto.common.enums

enum class StudySortType(private val state: String) {
    ACTIVE("활성"),
    FINISHED("완료");

    fun getStudySortType(studySortType: StudySortType): String {
        return studySortType.state
    }
}
