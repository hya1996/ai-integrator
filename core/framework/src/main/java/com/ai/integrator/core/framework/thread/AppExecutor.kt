package com.ai.integrator.core.framework.thread

import com.ai.integrator.core.framework.util.DeviceUtils
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.math.max

object AppExecutor {
    val ioExecutor by lazy {
        Executors.newFixedThreadPool(2, AppDefaultThreadFactory("app-io-thread", 3))
    }

    val networkExecutor by lazy {
        Executors.newFixedThreadPool(3, AppDefaultThreadFactory("app-network-thread", 3))
    }

    val backgroundExecutor by lazy {
        val poolSize = max(DeviceUtils.cpuCoreCount + 2, 4)
        ThreadPoolExecutor(poolSize, poolSize, 10L, TimeUnit.SECONDS, LinkedBlockingQueue(),
            AppDefaultThreadFactory("app-background-thread", 3)
        ).apply {
            allowCoreThreadTimeOut(true)
        }
    }
}