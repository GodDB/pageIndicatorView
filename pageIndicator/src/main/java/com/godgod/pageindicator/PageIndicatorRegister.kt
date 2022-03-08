package com.godgod.pageindicator

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.connectTo(pageIndicatorView: PageIndicatorView) {
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            pageIndicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    })
}
