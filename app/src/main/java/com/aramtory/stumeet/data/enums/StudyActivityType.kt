package com.aramtory.stumeet.data.enums

import android.content.Context
import com.aramtory.stumeet.R

enum class StudyActivityType(private val activityResId: Int) {
    MEET(R.string.enum_study_activity_type_meet),
    ASSIGNMENT(R.string.enum_study_activity_type_assignment),
    DEFAULT(R.string.enum_study_activity_type_default);

    fun getStudyActivityResId(): Int {
        return this.activityResId
    }

    companion object {
        fun getStudyActivity(context: Context, category: String?): StudyActivityType? {
            return when (category) {
                MEET.name -> MEET
                ASSIGNMENT.name -> ASSIGNMENT
                DEFAULT.name -> DEFAULT
                else -> null
            }
        }

        fun <T> doWhenStudyActivityType(
            category: String,
            whenMeet: () -> T?= { null },
            whenAssignment: () -> T?= { null },
            whenDefault: () -> T?= { null },
        ): T?  {
            return when (category) {
                MEET.name -> whenMeet()
                ASSIGNMENT.name -> whenAssignment()
                DEFAULT.name -> whenDefault()
                else -> null
            }
        }
    }
}
