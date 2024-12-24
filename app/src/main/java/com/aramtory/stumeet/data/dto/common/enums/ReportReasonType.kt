package com.aramtory.stumeet.data.dto.common.enums

enum class ReportReasonType(private val reason: String) {
    COMMERCIAL_ADVERTISEMENT_AND_SPAM("상업적 광고 및 스팸"),
    PORNOGRAPHY_AND_HARMFUL_ACTIVITIES("음란물 및 유해성 활동"),
    PROFANITY_AND_SLANDER("욕설 및 비방"),
    FRAUD_OR_IMPERSONATION("사기 또는 사칭"),
    FLOODING("도배"),
    OTHERS("기타");

    fun getReportReasonType(reportReasonType: ReportReasonType): String {
        return reportReasonType.reason
    }
}
