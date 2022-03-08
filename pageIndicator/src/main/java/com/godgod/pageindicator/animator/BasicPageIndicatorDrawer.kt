package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import com.godgod.pageindicator.PageIndicatorDrawerFactory
import com.godgod.pageindicator.PageIndicatorView

internal class BasicPageIndicatorDrawer : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        canvas.drawCircle(indicator.cx, indicator.cy, indicator.circleRadius, paint)
    }

    class Factory : PageIndicatorDrawerFactory<BasicPageIndicatorDrawer> {
        override fun create(): BasicPageIndicatorDrawer {
            return BasicPageIndicatorDrawer()
        }
    }
}