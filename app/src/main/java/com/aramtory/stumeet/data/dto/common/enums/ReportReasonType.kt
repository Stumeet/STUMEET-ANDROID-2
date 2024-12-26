package com.aramtory.stumeet.data.dto.common.enums

import com.aramtory.stumeet.R

enum class ReportReasonType(private val reasonResId: Int) {
    COMMERCIAL_ADVERTISEMENT_AND_SPAM(R.string.enum_reason_spam),
    PORNOGRAPHY_AND_HARMFUL_ACTIVITIES(R.string.enum_reason_harmful),
    PROFANITY_AND_SLANDER(R.string.enum_reason_slander),
    FRAUD_OR_IMPERSONATION(R.string.enum_reason_fraud),
    FLOODING(R.string.enum_reason_flooding),
    OTHERS(R.string.enum_reason_others);

    fun getReportReasonType(reportReasonType: ReportReasonType): Int {
        return reportReasonType.reasonResId
    }
}
