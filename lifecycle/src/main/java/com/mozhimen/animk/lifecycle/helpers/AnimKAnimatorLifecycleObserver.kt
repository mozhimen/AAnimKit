package com.mozhimen.animk.lifecycle.helpers

import android.animation.Animator
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiCall_BindViewLifecycle
import com.mozhimen.kotlin.lintk.optins.OApiInit_ByLazy
import com.mozhimen.kotlin.utilk.android.animation.cancel_removeAll_AllUpdateListeners

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
class AnimKAnimatorLifecycleObserver(private var _animator: Animator?) : BaseWakeBefDestroyLifecycleObserver() {
    override fun onDestroy(owner: LifecycleOwner) {
        _animator?.cancel_removeAll_AllUpdateListeners()
        _animator = null
        super.onDestroy(owner)
    }
}