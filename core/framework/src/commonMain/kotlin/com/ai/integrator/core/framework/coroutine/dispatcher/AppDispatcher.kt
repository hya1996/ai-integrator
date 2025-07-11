package com.ai.integrator.core.framework.coroutine.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

object AppDispatcher {
    val Main: CoroutineDispatcher by lazy {
        Dispatchers.Main
    }

    val IO: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }

    val Network: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }

    val Background: CoroutineDispatcher by lazy {
        Dispatchers.Default
    }
}