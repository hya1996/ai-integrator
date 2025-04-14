package com.ai.integrator.core.framework.thread.executor

import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

class AppDefaultThreadFactory(
    private val baseName: String,
    private val priority: Int
) : ThreadFactory {
    private val threadFactory = Executors.defaultThreadFactory()
    private val threadCount = AtomicInteger(0)

    override fun newThread(runnable: Runnable?): Thread {
        return threadFactory.newThread(runnable).apply {
            name = "$baseName-${threadCount.getAndIncrement()}"
            priority = this@AppDefaultThreadFactory.priority
        }
    }
}