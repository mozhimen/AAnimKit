package com.mozhimen.animk.builder.test

import android.os.Bundle
import com.mozhimen.animk.builder.test.databinding.ActivityAnimkBinding
import com.mozhimen.bindk.bases.activity.databinding.BaseActivityVDB

/**
 * @ClassName AnimKActivity
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/19 23:14
 * @Version 1.0
 */
class AnimKActivity : BaseActivityVDB<ActivityAnimkBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
//        animk.animkFormat.animationFormat.kind.build
//        ----.-------------.IAnimation-----.
//        AnimationTranslationType.TO_TOP_HIDE.build()
    }
}