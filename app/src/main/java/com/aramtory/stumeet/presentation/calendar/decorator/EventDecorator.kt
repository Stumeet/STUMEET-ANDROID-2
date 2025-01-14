package com.aramtory.stumeet.presentation.calendar.decorator

import android.graphics.Color
import com.aramtory.stumeet.presentation.calendar.custom.CustomDotSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(
    private val datesWithEvents: Set<CalendarDay>,
    private val marginTop: Int,
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return datesWithEvents.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        val dotSpan = CustomDotSpan(8f, Color.parseColor("#038175"), marginTop.toFloat())
        view.addSpan(dotSpan)
    }
}
