package com.mozhimen.animk.lifecycle.helpers

import android.animation.Animator
import android.view.animation.Animation
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.animation.cancel_removeAll_AllUpdateListeners
import com.mozhimen.kotlin.utilk.android.view.cancel_setAnimationListener_null

/**
 * @ClassName AnimKLifecycleObserver
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/3/7
 * @Version 1.0
 */
@OApiInit_ByLazy
@OApiCall_BindLifecycle
@OApiCall_BindViewLifecycle
class AnimKAnimationLifecycleObserver(private var _animation: Animation?) : BaseWakeBefDestroyLifecycleObserver() {
    override fun onDestroy(owner: LifecycleOwner) {
        _animation?.cancel_setAnimationListener_null()
        _animation = null
        super.onDestroy(owner)
    }
}