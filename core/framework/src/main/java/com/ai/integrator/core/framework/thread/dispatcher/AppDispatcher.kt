package com.ai.integrator.core.framework.thread.dispatcher

import com.ai.integrator.core.framework.thread.executor.AppExecutor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher

object AppDispatcher {
    val Main: CoroutineDispatcher by lazy {
        Dispatchers.Main
    }

    val IO: CoroutineDispatcher by lazy {
        AppExecutor.ioExecutor.asCoroutineDispatcher()
    }

    val Network: CoroutineDispatcher by lazy {
        AppExecutor.networkExecutor.asCoroutineDispatcher()
    }

    val Background: CoroutineDispatcher by lazy {
        AppExecutor.backgroundExecutor.asCoroutineDispatcher()
    }
}