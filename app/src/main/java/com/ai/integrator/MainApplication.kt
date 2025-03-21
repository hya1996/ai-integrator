package com.ai.integrator

import com.ai.integrator.core.framework.application.BaseApplication
import com.ai.integrator.core.framework.util.AppUtils

class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        AppUtils.init(this)
    }
}