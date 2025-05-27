package com.ai.integrator.im.session

import androidx.annotation.CallSuper
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.flow.collectIn
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.im.IMCenter
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

abstract class IMSessionController<T : IMSession>(
    protected val tag: String,
    protected val sessionScope: CoroutineScope = CoroutineScope(AppDispatcher.IO + SupervisorJob())
) {
    abstract val sessions: Flow<Map<String, T>>

    abstract val curSession: Flow<T?>

    abstract val curMessages: Flow<List<IMMessage<*>>>

    protected abstract val supportMessageHandlers: List<HandlerKey<*>>

    protected var messageNotifyListenJob: Job? = null

    init {
        initMessage()
    }

    protected open fun initMessage() {
        messageNotifyListenJob?.cancel()
        messageNotifyListenJob = IMCenter.messageNotify
            .filter { it.handlerKey in supportMessageHandlers }
            .collectIn(sessionScope) {
                Log.d(tag, "message notify: $it")
                onReceiveMessage(it.message)
            }
    }

    protected abstract fun onReceiveMessage(message: IMMessage<*>)

    @CallSuper
    open fun destroy() {
        messageNotifyListenJob?.cancel()
    }
}