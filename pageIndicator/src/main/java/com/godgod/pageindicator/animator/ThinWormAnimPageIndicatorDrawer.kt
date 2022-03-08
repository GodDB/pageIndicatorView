package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.godgod.pageindicator.PageIndicatorDrawerFactory
import com.godgod.pageindicator.PageIndicatorView

internal class ThinWormAnimPageIndicatorDrawer : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        if (positionOffset <= 0.5f) {
            drawMoveFrontAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        } else {
            drawTailConstrictAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        }
    }

    private fun drawMoveFrontAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animWidthValue = positionOffset
        val animHeightValue = if (positionOffset <= 0.1) positionOffset * 10f else 1f
        val left = indicator.cx - indicator.circleRadius + (animWidthValue * ((indicator.circleRadius * 2) + itemGap))
        val right = left + (indicator.circleRadius * 2)
        val top = indicator.cy - indicator.circleRadius
        val bottom = top + (indicator.circleRadius * 2)
        val rect = RectF(left, top + (0.5f * indicator.circleRadius * animHeightValue), right, bottom - (0.5f * (indicator.circleRadius * animHeightValue)))
        canvas.drawRoundRect(rect, indicator.circleRadius, indicator.circleRadius, paint)
    }

    private fun drawTailConstrictAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animWidthValue = positionOffset
        val animHeightValue = if (positionOffset >= 0.9) (1 - positionOffset) * 10f else 1f
        val left = indicator.cx - indicator.circleRadius + (animWidthValue * ((indicator.circleRadius * 2) + itemGap))
        val right = left + (indicator.circleRadius * 2)
        val top = indicator.cy - indicator.circleRadius
        val bottom = top + (indicator.circleRadius * 2)
        val rect = RectF(left, top + (0.5f * indicator.circleRadius * animHeightValue), right, bottom - (0.5f * (indicator.circleRadius * animHeightValue)))
        canvas.drawRoundRect(rect, indicator.circleRadius, indicator.circleRadius, paint)
    }

    class Factory : PageIndicatorDrawerFactory<ThinWormAnimPageIndicatorDrawer> {
        override fun create(): ThinWormAnimPageIndicatorDrawer {
            return ThinWormAnimPageIndicatorDrawer()
        }
    }
}