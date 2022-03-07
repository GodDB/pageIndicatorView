package com.godgod.pageindicator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.godgod.pageindicator.animator.*
import com.godgod.pageindicator.animator.PageIndicatorDrawer
import com.godgod.pageindicator.animator.ThinWormAnimPageIndicatorDrawer

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val unSelectPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.cardview_dark_background)
        isAntiAlias = true
    }

    private val selectPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.design_default_color_primary_variant)
        isAntiAlias = true
    }

    private val pageIndicatorDrawer: PageIndicatorDrawer = ThinWormAnimPageIndicatorDrawer()

    private val circleRadius = 50f
    private val itemGap = 20
    private var itemCount: Int = 0

    private var selectedPosition: Int = 0
    private var selectedPositionOffset: Float = 0f
    private var selectedPositionOffsetPixel: Int = 0

    private val indicatorMap: HashMap<Int, Indicator> = hashMapOf()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            val width = itemCount * (circleRadius * 2 + itemGap) - itemGap
            val height = circleRadius * 2
            setMeasuredDimension((width).toInt(), height.toInt())
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        indicatorMap.clear()

        var indicator = Indicator(cx = circleRadius, cy = height / 2f, circleRadius = circleRadius)
        for (i in 0 until itemCount) {
            indicatorMap[i] = indicator
            drawUnSelectCircle(canvas, indicator)

            indicator = indicator.plusCx((circleRadius * 2) + itemGap)
        }

        indicatorMap[selectedPosition]?.let {
            pageIndicatorDrawer.draw(canvas, selectPaint, itemGap, selectedPosition, selectedPositionOffset, it)
        }

    }

    private fun drawUnSelectCircle(canvas: Canvas, indicator: Indicator) {
        canvas.drawCircle(indicator.cx, indicator.cy, indicator.circleRadius, unSelectPaint)
    }

    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        this.selectedPosition = position
        this.selectedPositionOffset = positionOffset
        this.selectedPositionOffsetPixel = positionOffsetPixels
        invalidate()
    }

    fun setItemCount(count: Int) {
        itemCount = count
        requestLayout()
    }


    internal data class Indicator(val cx: Float, val cy: Float, val circleRadius: Float) {

        fun plusCx(otherCx: Float): Indicator {
            return this.copy(cx = cx + otherCx)
        }

        fun plusCy(otherCy: Float): Indicator {
            return this.copy(cy = cy + otherCy)
        }

        fun plusCxCy(otherCx: Float, otherCy: Float): Indicator {
            return this.copy(cx = cx + otherCx, cy = cy + otherCy)
        }

        fun minusCx(otherCx: Float): Indicator {
            return this.copy(cx = cx - otherCx)
        }

        fun minusCy(otherCy: Float): Indicator {
            return this.copy(cy = cy - otherCy)
        }

        fun minusCxCy(otherCx: Float, otherCy: Float): Indicator {
            return this.copy(cx = cx - otherCx, cy = cy - otherCy)
        }
    }
}


