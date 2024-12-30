package com.aramtory.stumeet.coreui.view

import android.content.Context
import com.aramtory.stumeet.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


// 문자열을 Date 객체로 변환
fun String.toLocalDateTime(
    inputFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
): LocalDateTime? =
    runCatching {
        LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }.getOrNull()

// 문자열을 커스텀 날짜 형식으로 변환
fun String.toCustomDateFormat(
    inputFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME,
    outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
): String =
    runCatching {
        LocalDateTime.parse(this, inputFormatter).format(outputFormatter)
    }.getOrElse { "Invalid date format" }

// 남은 시간 계산
fun String.calculateDurationBetweenCurrentTime(
    context: Context,
    isRemainCalculate: Boolean = true,
): String? {
    val now = LocalDateTime.now(ZoneId.systemDefault())
    val targetDateTime = runCatching {
        LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }.getOrNull() ?: return null

    // 기간 계산
    val duration = if (isRemainCalculate) {
        if (now.isAfter(targetDateTime)) return null
        Duration.between(now, targetDateTime)
    } else {
        if (now.isBefore(targetDateTime)) return null
        Duration.between(targetDateTime, now)
    }

    // 기간멸 day, minutes 추출
    val days = duration.toDays()
    val minutes = duration.toMinutes() % 60

    return when {
        duration.toMinutes() < 60 -> context.getString(
            if (isRemainCalculate) R.string.tv_activity_remain_time_minutes else R.string.tv_activity_before_time_minutes,
            minutes
        )

        duration.toHours() < 24 -> context.getString(
            if (isRemainCalculate) R.string.tv_activity_remain_time_hours else R.string.tv_activity_before_time_hours,
            duration.toHours(),
            minutes
        )

        days < 7 -> context.getString(
            if (isRemainCalculate) R.string.tv_activity_remain_time_days else R.string.tv_activity_before_time_days,
            days
        )

        days < 28 -> context.getString(
            if (isRemainCalculate) R.string.tv_activity_remain_time_weeks else R.string.tv_activity_before_time_weeks,
            days / 7
        )

        days < 365 -> context.getString(
            if (isRemainCalculate) R.string.tv_activity_remain_time_months else R.string.tv_activity_before_time_months,
            days / 30,
            (days % 30) / 7
        )

        else -> targetDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
    }
}

