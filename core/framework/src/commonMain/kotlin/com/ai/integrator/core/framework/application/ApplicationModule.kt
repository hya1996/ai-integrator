@file:OptIn(ExperimentalAtomicApi::class)

package com.ai.integrator.core.framework.application

import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.ExperimentalAtomicApi

abstract class ApplicationModule {
    private val isInit = AtomicBoolean(false)

    open fun onInit() {}

    fun init() {
        if (isInit.compareAndSet(false, true)) {
            onInit()
        }
    }
}