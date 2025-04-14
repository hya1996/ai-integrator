package com.ai.integrator.core.framework.util

object DeviceUtils {
    val cpuCoreCount: Int
        get() = Runtime.getRuntime().availableProcessors()
}