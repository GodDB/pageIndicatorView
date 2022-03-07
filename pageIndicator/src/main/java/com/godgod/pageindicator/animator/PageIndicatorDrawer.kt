package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import com.godgod.pageindicator.PageIndicatorView

internal abstract class PageIndicatorDrawer {

    abstract fun draw(canvas: Canvas, paint: Paint, itemGap : Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator)
}