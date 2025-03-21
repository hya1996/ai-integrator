package com.ai.integrator.core.framework.util

import com.ai.integrator.core.framework.BuildConfig

object EnvUtils {
    val isDebug: Boolean
        get() = BuildConfig.DEBUG

    val isRelease: Boolean
        get() = !BuildConfig.DEBUG
}