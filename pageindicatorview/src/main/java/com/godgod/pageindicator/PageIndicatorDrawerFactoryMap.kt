package com.godgod.pageindicator

import com.godgod.pageindicator.animator.*

internal class PageIndicatorDrawerFactoryMap(
    private val innerMap: MutableMap<PageIndicatorType, PageIndicatorDrawerFactory<*>> = mutableMapOf()
) : Map<PageIndicatorType, PageIndicatorDrawerFactory<*>> by innerMap {

    init {
        innerMap[PageIndicatorType.BASIC] = BasicPageIndicatorDrawer.Factory()
        innerMap[PageIndicatorType.FADE] = FadeAnimPageIndicatorDrawer.Factory()
        innerMap[PageIndicatorType.JUMP] = JumpAnimPageIndicator.Factory()
        innerMap[PageIndicatorType.MOVE] = MoveAnimPageIndicatorDrawer.Factory()
        innerMap[PageIndicatorType.THIN_WORM] = ThinWormAnimPageIndicatorDrawer.Factory()
        innerMap[PageIndicatorType.WORM] = WormAnimPageIndicatorDrawer.Factory()
    }
}