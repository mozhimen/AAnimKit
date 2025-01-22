package com.mozhimen.animk.builder.mos

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import com.mozhimen.kotlin.utilk.wrapper.UtilKRes

/**
 * @ClassName MAnimationConfig
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Version 1.0
 */
data class MAnimKConfig(
    var fillBefore: Boolean = false,
    var fillAfter: Boolean = true,
    var duration: Long = 300L,
    var interpolator: Interpolator = AccelerateDecelerateInterpolator()
)