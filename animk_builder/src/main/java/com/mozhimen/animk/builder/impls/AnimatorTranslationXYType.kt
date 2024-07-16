package com.mozhimen.animk.builder.impls

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View
import com.mozhimen.animk.builder.bases.BaseAnimatorType
import com.mozhimen.animk.builder.commons.IAnimViewType
import com.mozhimen.animk.builder.cons.EDirection
import com.mozhimen.animk.builder.mos.MAnimKConfig
import java.lang.ref.WeakReference

/**
 * @ClassName TranslationConfig
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/17 23:03
 * @Version 1.0
 */
open class AnimatorTranslationXYType : BaseAnimatorType<AnimatorTranslationXYType, Nothing>(), IAnimViewType<AnimatorTranslationXYType> {
    private var _fromX = 0f
    private var _toX = 0f
    private var _fromY = 0f
    private var _toY = 0f
    private var _isPercentageFromX = false
    private var _isPercentageToX = false
    private var _isPercentageFromY = false
    private var _isPercentageToY = false
    private var _viewRef: WeakReference<View>? = null

    override fun setViewRef(view: View): AnimatorTranslationXYType {
        _viewRef = WeakReference(view)
        return this
    }

    fun from(vararg directions: EDirection): AnimatorTranslationXYType {
        if (directions.isNotEmpty()) {
            _fromY = 0f
            _fromX = _fromY
            var flag = 0
            for (direction in directions) {
                flag = flag or direction.flag
            }
            if (EDirection.isDirectionFlag(EDirection.LEFT, flag)) {
                fromX(_fromX - 1, true)
            }
            if (EDirection.isDirectionFlag(EDirection.RIGHT, flag)) {
                fromX(_fromX + 1, true)
            }
            if (EDirection.isDirectionFlag(EDirection.CENTER_HORIZONTAL, flag)) {
                fromX(_fromX + 0.5f, true)
            }
            if (EDirection.isDirectionFlag(EDirection.TOP, flag)) {
                fromY(_fromY - 1, true)
            }
            if (EDirection.isDirectionFlag(EDirection.BOTTOM, flag)) {
                fromY(_fromY + 1, true)
            }
            if (EDirection.isDirectionFlag(EDirection.CENTER_VERTICAL, flag)) {
                fromY(_fromY + 0.5f, true)
            }
            _isPercentageToY = true
            _isPercentageToX = true
            _isPercentageFromY = true
            _isPercentageFromX = true
        }
        return this
    }

    fun to(vararg directions: EDirection): AnimatorTranslationXYType {
        if (directions.isNotEmpty()) {
            _toY = 0f
            _toX = _toY
            var flag = 0
            for (direction in directions) {
                flag = flag or direction.flag
            }
            if (EDirection.isDirectionFlag(EDirection.LEFT, flag)) {
                _toX += -1f
            }
            if (EDirection.isDirectionFlag(EDirection.RIGHT, flag)) {
                _toX += 1f
            }
            if (EDirection.isDirectionFlag(EDirection.CENTER_HORIZONTAL, flag)) {
                _toX += .5f
            }
            if (EDirection.isDirectionFlag(EDirection.TOP, flag)) {
                _toY += -1f
            }
            if (EDirection.isDirectionFlag(EDirection.BOTTOM, flag)) {
                _toY += 1f
            }
            if (EDirection.isDirectionFlag(EDirection.CENTER_VERTICAL, flag)) {
                _toY += .5f
            }
            _isPercentageToY = true
            _isPercentageToX = true
            _isPercentageFromY = true
            _isPercentageFromX = true
        }
        return this
    }

    fun fromX(fromX: Float): AnimatorTranslationXYType {
        fromX(fromX, false)
        return this
    }

    fun toX(toX: Float): AnimatorTranslationXYType {
        toX(toX, false)
        return this
    }

    fun fromY(fromY: Float): AnimatorTranslationXYType {
        fromY(fromY, false)
        return this
    }

    fun toY(toY: Float): AnimatorTranslationXYType {
        toY(toY, false)
        return this
    }

    fun fromX(fromX: Int): AnimatorTranslationXYType {
        fromX(fromX.toFloat(), false)
        return this
    }

    fun toX(toX: Int): AnimatorTranslationXYType {
        toX(toX.toFloat(), false)
        return this
    }

    fun fromY(fromY: Int): AnimatorTranslationXYType {
        fromY(fromY.toFloat(), false)
        return this
    }

    fun toY(toY: Int): AnimatorTranslationXYType {
        toY(toY.toFloat(), false)
        return this
    }

    fun fromX(fromX: Float, percentage: Boolean): AnimatorTranslationXYType {
        _isPercentageFromX = percentage
        this._fromX = fromX
        return this
    }

    fun toX(toX: Float, percentage: Boolean): AnimatorTranslationXYType {
        _isPercentageToX = percentage
        this._toX = toX
        return this
    }

    fun fromY(fromY: Float, percentage: Boolean): AnimatorTranslationXYType {
        _isPercentageFromY = percentage
        this._fromY = fromY
        return this
    }

    fun toY(toY: Float, percentage: Boolean): AnimatorTranslationXYType {
        _isPercentageToY = percentage
        this._toY = toY
        return this
    }

    override fun build(animKConfig: MAnimKConfig): Animator {
        val animatorSet = AnimatorSet()
        val translationXProperty =
            if (_isPercentageFromX && _isPercentageToY)
                object : FloatPropertyCompat<View>(View.X.name) {
                    override fun setValue(obj: View, value: Float) {
                        obj.x = obj.width * value
                    }

                    override operator fun get(obj: View): Float {
                        return obj.x
                    }
                }
            else
                View.X
        val translationYProperty =
            if (_isPercentageFromY && _isPercentageToY)
                object : FloatPropertyCompat<View>(View.Y.name) {
                    override fun setValue(obj: View, value: Float) {
                        obj.y = obj.height * value
                    }

                    override operator fun get(obj: View): Float {
                        return obj.y
                    }
                }
            else
                View.Y
        val translationX = if (_viewRef?.get() != null) {
            ObjectAnimator.ofFloat(_viewRef?.get()!!, translationXProperty, _fromX, _toX)
        } else
            ObjectAnimator.ofFloat(null, translationXProperty, _fromX, _toX)
        val translationY = if (_viewRef?.get() != null) {
            ObjectAnimator.ofFloat(_viewRef?.get()!!, translationYProperty, _fromY, _toY)
        } else
            ObjectAnimator.ofFloat(null, translationYProperty, _fromY, _toY)
        animatorSet.playTogether(translationX, translationY)
        format(animKConfig, animatorSet)
        return animatorSet
    }

    abstract class FloatPropertyCompat<T>(name: String) : Property<T, Float>(Float::class.java, name) {
        /**
         * A type-specific variant of [.set] that is faster when dealing
         * with fields of type `float`.
         */
        abstract fun setValue(obj: T, value: Float)

        override operator fun set(obj: T, value: Float) {
            setValue(obj, value)
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    companion object {
        val FROM_LEFT_SHOW: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            from(EDirection.LEFT)
        }
        val FROM_TOP_SHOW: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            from(EDirection.TOP)
        }
        val FROM_RIGHT_SHOW: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            from(EDirection.RIGHT)
        }
        val FROM_BOTTOM_SHOW: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            from(EDirection.BOTTOM)
        }
        val TO_LEFT_HIDE: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            to(EDirection.LEFT)
        }
        val TO_TOP_HIDE: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            to(EDirection.TOP)
        }
        val TO_RIGHT_HIDE: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            to(EDirection.RIGHT)
        }
        val TO_BOTTOM_HIDE: AnimatorTranslationXYType = AnimatorTranslationXYType().apply {
            to(EDirection.BOTTOM)
        }
    }
}
