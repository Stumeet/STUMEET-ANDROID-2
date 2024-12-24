package com.aramtory.stumeet.data.dto.common.enums

enum class ReportType(private val type: String) {
    STUDY("스터디"),
    ACTIVITY("활동");

    fun getReportType(reportType: ReportType): String {
        return reportType.type
    }
}
