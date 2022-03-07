package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.godgod.pageindicator.PageIndicatorView

internal class WormAnimPageIndicatorDrawer : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        if (positionOffset <= 0.5) {
            drawMoveFrontAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        } else {
            drawTailConstrictAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        }
    }

    private fun drawMoveFrontAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animValue = positionOffset * 2
        val left = indicator.cx - indicator.circleRadius
        val right = left + (indicator.circleRadius * 2) + (animValue * ((indicator.circleRadius * 2) + itemGap))
        val top = indicator.cy - indicator.circleRadius
        val bottom = top + (indicator.circleRadius * 2)
        val rect = RectF(left, top, right, bottom)
        canvas.drawRoundRect(rect, indicator.circleRadius, indicator.circleRadius, paint)
    }

    private fun drawTailConstrictAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animValue = (positionOffset - 0.5f)*2
        val left = indicator.cx - indicator.circleRadius + (((indicator.circleRadius * 2) + itemGap) * animValue)
        val right = indicator.cx - indicator.circleRadius + (indicator.circleRadius * 2) + ((indicator.circleRadius * 2) + itemGap)
        val top = indicator.cy - indicator.circleRadius
        val bottom = top + (indicator.circleRadius * 2)
        val rect = RectF(left, top, right, bottom)
        canvas.drawRoundRect(rect, indicator.circleRadius, indicator.circleRadius, paint)
    }

}