package com.aramtory.stumeet.presentation.calendar.decorator

import android.content.Context
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import android.text.style.LineHeightSpan
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.context.colorOf
import com.aramtory.stumeet.coreui.context.drawableOf
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class CurrentAndSelectedDayDecorator(
    private val context: Context,
    private val selectedDate: CalendarDay = CalendarDay.today(),
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == selectedDate
    }

    override fun decorate(view: DayViewFacade) {
        // 선택된 날짜에 네모난 배경 추가
        val drawable = context.drawableOf(R.drawable.shape_primary_100_radius_8)
        drawable?.let { view.setSelectionDrawable(it) }

        // 텍스트 색상 변경
        view.addSpan(ForegroundColorSpan(context.colorOf(R.color.primary_600)))

        view.addSpan(object : LineHeightSpan {
            override fun chooseHeight(
                text: CharSequence?,
                start: Int,
                end: Int,
                spanstartv: Int,
                v: Int,
                fm: Paint.FontMetricsInt?
            ) {
                fm?.apply {
                    ascent -= 4
                    descent += 4
                }
            }
        })
    }
}