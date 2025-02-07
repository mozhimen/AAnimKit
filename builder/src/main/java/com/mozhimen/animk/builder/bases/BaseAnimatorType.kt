package com.mozhimen.animk.builder.bases

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import com.mozhimen.animk.builder.commons.IAnimatorType
import com.mozhimen.animk.builder.commons.IAnimatorUpdateListener
import com.mozhimen.animk.builder.mos.MAnimKConfig
import com.mozhimen.kotlin.elemk.kotlin.cons.CSuppress
import com.mozhimen.kotlin.utilk.android.animation.UtilKAnimatorWrapper

/**
 * @ClassName BaseAnimatorType
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Version 1.0
 */
@Suppress(CSuppress.UNCHECKED_CAST)
abstract class BaseAnimatorType<TYPE, UPDATE_VALUE> : BaseProperty<TYPE>(), IAnimatorType {

    protected var _animatorListenerAdapters: ArrayList<AnimatorListenerAdapter>? = null
    protected var _animatorUpdateListeners: ArrayList<IAnimatorUpdateListener<UPDATE_VALUE>>? = null
//    protected var _isAutoClearListener = true

//    inner class AutoClearAnimatorListenerAdapter : AnimatorListenerAdapter() {
//        override fun onAnimationCancel(animation: Animator) {
//            clearAllListeners(animation)
//        }
//
//        override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
//            clearAllListeners(animation)
//        }
//
//        override fun onAnimationEnd(animation: Animator) {
//            clearAllListeners(animation)
//        }
//    }

//    fun isAutoClearListener(isAutoClearListener: Boolean): TYPE {
//        _isAutoClearListener = isAutoClearListener
//        return this as TYPE
//    }

    fun addAnimatorListener(listener: AnimatorListenerAdapter): TYPE {
        if (_animatorListenerAdapters == null) {
            _animatorListenerAdapters = ArrayList()
        }
        if (!_animatorListenerAdapters!!.contains(listener))
            _animatorListenerAdapters!!.add(listener)
        return this as TYPE
    }

    fun addAnimatorUpdateListener(listener: IAnimatorUpdateListener<UPDATE_VALUE>): TYPE {
        if (_animatorUpdateListeners == null) {
            _animatorUpdateListeners = ArrayList()
        }
        if (!_animatorUpdateListeners!!.contains(listener))
            _animatorUpdateListeners!!.add(listener)
        return this as TYPE
    }

    fun clearAllListeners(animation: Animator) {
        UtilKAnimatorWrapper.cancel_removeAll_AllUpdateListeners(animation)
        _animatorUpdateListeners?.clear()
        _animatorListenerAdapters?.clear()
    }

    override fun format(animKConfig: MAnimKConfig, anim: Animator) {
        super.format(animKConfig, anim)
//        if (_isAutoClearListener)
//            anim.addListener(AutoClearAnimatorListenerAdapter())
        _animatorListenerAdapters?.forEach {
            anim.addListener(it)
        }
        if (anim is ValueAnimator) {
            _animatorUpdateListeners?.forEach { lis ->
                anim.addUpdateListener {
                    lis.onChange(it.animatedValue as? UPDATE_VALUE?)
                }
            }
        }
        anim.interpolator = _interpolator ?: animKConfig.interpolator
        anim.duration = _duration ?: animKConfig.duration
    }
}