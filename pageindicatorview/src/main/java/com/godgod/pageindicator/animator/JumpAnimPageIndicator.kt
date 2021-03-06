package com.godgod.pageindicator.animator

import android.graphics.Canvas
import android.graphics.Paint
import com.godgod.pageindicator.PageIndicatorDrawerFactory
import com.godgod.pageindicator.PageIndicatorView
import kotlin.math.tan

internal class JumpAnimPageIndicator : PageIndicatorDrawer() {

    override fun draw(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        if (positionOffset <= 0.5f) {
            drawJumpUpAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        } else {
            drawJumpDownAnim(canvas, paint, itemGap, position, positionOffset, indicator)
        }
    }

    private fun drawJumpUpAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animValue = positionOffset
        val scaleAnimValue = 1 - (0.8f *positionOffset)
        val distance = indicator.circleRadius*2 + (itemGap)
        val jumpY = -(tan(Math.toRadians(60.0)) * distance).toFloat()
        canvas.drawCircle(indicator.cx + (distance * animValue), indicator.cy + (jumpY * animValue), indicator.circleRadius * scaleAnimValue, paint)
    }

    private fun drawJumpDownAnim(canvas: Canvas, paint: Paint, itemGap: Int, position: Int, positionOffset: Float, indicator: PageIndicatorView.Indicator) {
        val animValue = positionOffset - 0.5f
        val scaleAnimValue = 0.6f + (0.8f * (positionOffset - 0.5f))
        val distance = indicator.circleRadius*2 + (itemGap)
        val prevX = distance * 0.5f
        val prevY = -(tan(Math.toRadians(60.0)) * distance).toFloat() * 0.5f
        val jumpY = -(tan(Math.toRadians(300.0)) * distance).toFloat()
        canvas.drawCircle(indicator.cx + prevX + (distance * animValue), indicator.cy + prevY + (jumpY * animValue), indicator.circleRadius * scaleAnimValue, paint)
    }

    class Factory : PageIndicatorDrawerFactory<JumpAnimPageIndicator> {
        override fun create(): JumpAnimPageIndicator {
            return JumpAnimPageIndicator()
        }
    }
}