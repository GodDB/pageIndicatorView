package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import com.godgod.pageindicator.PageIndicatorView

internal class MoveAnimPageIndicatorDrawer : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val distance = indicator.circleRadius * 2 + itemGap
        canvas.drawCircle(indicator.cx + (distance * positionOffset), indicator.cy, indicator.circleRadius, paint)
    }
}