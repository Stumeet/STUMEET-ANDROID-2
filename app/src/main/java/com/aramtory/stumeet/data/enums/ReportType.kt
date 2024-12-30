package com.aramtory.stumeet.data.enums

import com.aramtory.stumeet.R

enum class ReportType(private val typeResId: Int) {
    STUDY(R.string.enum_report_study),
    ACTIVITY(R.string.enum_report_activity);

    fun getReportType(reportType: ReportType): Int {
        return reportType.typeResId
    }
}
