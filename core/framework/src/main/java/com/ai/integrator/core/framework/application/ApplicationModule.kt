package com.ai.integrator.core.framework.application

import java.util.concurrent.atomic.AtomicBoolean

abstract class ApplicationModule {
    private val isInit = AtomicBoolean(false)

    open fun onInit() {}

    fun init() {
        if (isInit.compareAndSet(false, true)) {
            onInit()
        }
    }
}