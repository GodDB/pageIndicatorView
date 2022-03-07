package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import com.godgod.pageindicator.PageIndicatorView

internal class FadeAnimPageIndicatorDrawer : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        if (positionOffset <= 0.5f) {
            drawFadeOutCurrentPositionAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        } else {
            drawFadeInNextPositionAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        }
    }

    private fun drawFadeOutCurrentPositionAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val alphaAnimValue = 1f - (positionOffset * 2)

        paint.useAlpha((255 * alphaAnimValue).toInt()) {
            canvas.drawCircle(indicator.cx, indicator.cy, indicator.circleRadius, this)
        }
    }

    private fun drawFadeInNextPositionAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val alphaAnimValue = positionOffset

        val cx = indicator.cx + (indicator.circleRadius*2) + itemGap
        val cy = indicator.cy

        paint.useAlpha((255 * alphaAnimValue).toInt()) {
            canvas.drawCircle(cx, cy, indicator.circleRadius, this)
        }
    }

    private inline fun Paint.useAlpha(alphaValue : Int, block : Paint.() -> Unit) {
        block(this.apply { alpha = alphaValue })
        this.alpha = 255
    }
}