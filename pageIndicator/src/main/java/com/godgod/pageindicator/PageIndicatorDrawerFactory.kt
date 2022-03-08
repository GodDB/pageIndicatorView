package com.godgod.pageindicator

import com.godgod.pageindicator.animator.PageIndicatorDrawer

internal interface PageIndicatorDrawerFactory<out T : PageIndicatorDrawer> {
    fun create(): T
}