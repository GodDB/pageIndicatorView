package com.godgod.pageindicator

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.godgod.pageindicator.animator.PageIndicatorDrawer

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var _unselectPaint: Paint? = null
    private val unselectPaint: Paint
        get() = _unselectPaint ?: Paint().apply {
            color = ContextCompat.getColor(context, R.color.cardview_dark_background)
            isAntiAlias = true
        }

    private var _selectPaint: Paint? = null
    private val selectPaint: Paint
        get() = _selectPaint ?: Paint().apply {
            color = ContextCompat.getColor(context, R.color.design_default_color_on_primary)
            isAntiAlias = true
        }

    private val pageIndicatorDrawerFactoryMap: Map<PageIndicatorType, PageIndicatorDrawerFactory<PageIndicatorDrawer>> = PageIndicatorDrawerFactoryMap()

    private var _pageIndicatorDrawer: PageIndicatorDrawer? = null
    private val pageIndicatorDrawer: PageIndicatorDrawer
        get() = _pageIndicatorDrawer ?: pageIndicatorDrawerFactoryMap[PageIndicatorType.BASIC]!!.create()

    private var circleRadius = 50f
    private var itemGap = 20
    private var itemCount: Int = 0

    private var selectedPosition: Int = 0
    private var selectedPositionOffset: Float = 0f
    private var selectedPositionOffsetPixel: Int = 0

    private val indicatorMap: HashMap<Int, Indicator> = hashMapOf()

    init {
        attrs?.let {
            setupAttrs(it)
        }
    }

    private fun setupAttrs(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.PageIndicatorView).use {
            val indicatorType = PageIndicatorType.values()[it.getInt(R.styleable.PageIndicatorView_indicator_type, 0)]
            setPageIndicatorType(indicatorType)
            val selectColor = it.getColor(R.styleable.PageIndicatorView_select_indicator_color, ContextCompat.getColor(context, R.color.design_default_color_on_primary))
            setSelectIndicatorColor(selectColor)
            val unselectColor = it.getColor(R.styleable.PageIndicatorView_unselect_indidator_color, ContextCompat.getColor(context, R.color.cardview_dark_background))
            setUnselectIndicatorColor(unselectColor)
            val indicatorCount = it.getInteger(R.styleable.PageIndicatorView_indicator_count, 0)
            setIndicatorCount(indicatorCount)
            val indicatorGap = it.getDimension(R.styleable.PageIndicatorView_indicator_gap, 0f)
            setIndicatorGap(indicatorGap.toInt())
            val indicatorCircleRadius = it.getDimension(R.styleable.PageIndicatorView_indicator_circle_radius, 0f)
            setIndicatorCircleRadius(indicatorCircleRadius)
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        check(widthMode == MeasureSpec.AT_MOST) {
            "require layout_width = WRAP_CONTENT"
        }

        check(heightMode == MeasureSpec.AT_MOST) {
            "require layout_height = WRAP_CONTENT"
        }

        val width = itemCount * (circleRadius * 2 + itemGap) - itemGap
        val height = circleRadius * 2
        setMeasuredDimension((width).toInt(), height.toInt())
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
        canvas.drawCircle(indicator.cx, indicator.cy, indicator.circleRadius, unselectPaint)
    }

    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        this.selectedPosition = position
        this.selectedPositionOffset = positionOffset
        this.selectedPositionOffsetPixel = positionOffsetPixels
        invalidate()
    }

    fun setIndicatorCount(count: Int) {
        itemCount = count
        requestLayout()
    }

    fun setPageIndicatorType(type: PageIndicatorType) {
        this._pageIndicatorDrawer = pageIndicatorDrawerFactoryMap[type]!!.create()
        invalidate()
    }

    fun setIndicatorCircleRadius(radius: Float) {
        this.circleRadius = radius
        requestLayout()
    }

    fun setIndicatorGap(gap: Int) {
        this.itemGap = gap
        requestLayout()
    }

    fun setSelectIndicatorColor(color: Int) {
        this._selectPaint = Paint().apply {
            this.isAntiAlias = true
            this.color = color
        }
        invalidate()
    }

    fun setUnselectIndicatorColor(color: Int) {
        this._unselectPaint = Paint().apply {
            this.isAntiAlias = true
            this.color = color
        }
        invalidate()
    }

    private inline fun TypedArray.use(block : (TypedArray) -> Unit) {
        block(this)
        this.recycle()
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


