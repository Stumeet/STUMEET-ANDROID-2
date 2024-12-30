package com.aramtory.stumeet.presentation.calendar.custom
import android.graphics.Canvas
import android.graphics.Paint
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class CustomDotSpan(
    private val radius: Float,
    private val color: Int,
    private val marginTop: Float,
) : DotSpan(radius, color) {

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        charSequence: CharSequence,
        start: Int,
        end: Int,
        lineHeight: Int
    ) {
        val oldColor = paint.color
        if (color != 0) {
            paint.color = color
        }

        // 점의 위치 계산 (위쪽 여백 적용)
        val cx = (left + right) / 2f
        val cy = bottom + marginTop

        canvas.drawCircle(cx, cy, radius, paint)
        paint.color = oldColor
    }
}
