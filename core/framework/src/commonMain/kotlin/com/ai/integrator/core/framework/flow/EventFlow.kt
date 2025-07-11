@file:OptIn(ExperimentalForInheritanceCoroutinesApi::class, ExperimentalAtomicApi::class)

package com.ai.integrator.core.framework.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.ExperimentalAtomicApi

interface EventFlow<T> : SharedFlow<T>

interface MutableEventFlow<T> : MutableSharedFlow<T>, EventFlow<T> {
    fun publish(event: T)
}

private class MutableEventFlowImpl<T>(
    private val sharedFlow: MutableSharedFlow<T>
) : MutableEventFlow<T>, MutableSharedFlow<T> by sharedFlow {
    override fun publish(event: T) {
        sharedFlow.tryEmit(event)
    }
}

private class ReadonlyEventFlow<T>(
    eventFlow: EventFlow<T>
) : EventFlow<T> by eventFlow

private class LazyEventFlowWrapper<T>(
    private val source: Flow<T>,
    private val eventFlow: MutableEventFlow<T>,
    private val scope: CoroutineScope
) : EventFlow<T> by eventFlow {
    private val isCollectedSource = AtomicBoolean(false)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        if (isCollectedSource.compareAndSet(false, true)) {
            source.collectIn(scope) {
                eventFlow.publish(it)
            }
        }
        eventFlow.collect(collector)
    }
}

fun <T> MutableEventFlow(): MutableEventFlow<T> {
    return MutableEventFlowImpl(
        MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    )
}

fun <T> Flow<T>.asEventFlow(scope: CoroutineScope): EventFlow<T> {
    return LazyEventFlowWrapper(this, MutableEventFlow(), scope)
}

fun <T> MutableEventFlow<T>.asEventFlow(): EventFlow<T> {
    return ReadonlyEventFlow(this)
}