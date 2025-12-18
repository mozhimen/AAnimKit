package com.mozhimen.animk.builder.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mozhimen.animk.builder.test.databinding.ActivityMainBinding
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.uik.databinding.bases.viewdatabinding.activity.BaseActivityVDB

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goAnimK(view: View) {
        startContext<AnimKActivity>()
    }
}