package com.mozhimen.animk.lifecycle

import android.animation.Animator
import android.view.animation.Animation
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.animk.lifecycle.helpers.AnimKAnimationLifecycleObserver
import com.mozhimen.animk.lifecycle.helpers.AnimKAnimatorLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy

/**
 * @ClassName AnimKBuilderLifecycle
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/3/7
 * @Version 1.0
 */
@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
fun Animator.bindLifecycle(lifecycleOwner: LifecycleOwner) {
    AnimKBuilderLifecycle.bindLifecycle(this, lifecycleOwner)
}

@OApiCall_BindViewLifecycle
@OApiCall_BindLifecycle
fun Animation.bindLifecycle(lifecycleOwner: LifecycleOwner) {
    AnimKBuilderLifecycle.bindLifecycle(this, lifecycleOwner)
}

/////////////////////////////////////////////////////////////////////////////////////

object AnimKBuilderLifecycle {
    @OApiCall_BindViewLifecycle
    @OApiCall_BindLifecycle
    @OptIn(OApiInit_ByLazy::class)
    @JvmStatic
    fun bindLifecycle(animator: Animator, lifecycleOwner: LifecycleOwner) {
        AnimKAnimatorLifecycleObserver(animator).bindLifecycle(lifecycleOwner)
    }

    @OApiCall_BindViewLifecycle
    @OApiCall_BindLifecycle
    @OptIn(OApiInit_ByLazy::class)
    @JvmStatic
    fun bindLifecycle(animation: Animation, lifecycleOwner: LifecycleOwner) {
        AnimKAnimationLifecycleObserver(animation).bindLifecycle(lifecycleOwner)
    }
}