package com.ai.integrator

import com.ai.integrator.core.framework.application.BaseApplication
import com.ai.integrator.core.framework.util.AppUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        AppUtils.init(this)
    }
}