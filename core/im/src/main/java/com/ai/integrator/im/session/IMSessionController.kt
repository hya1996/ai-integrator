package com.ai.integrator.im.session

import androidx.annotation.CallSuper
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.flow.collectIn
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.im.IMCenter
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter

abstract class IMSessionController<T : IMSession<T>>(
    protected val peerId: Long,
    protected val tag: String,
    protected val sessionScope: CoroutineScope = CoroutineScope(AppDispatcher.Background + SupervisorJob())
) {
    private val _sessions = MutableStateFlow<Map<String, T>>(emptyMap())
    val sessions = _sessions.asStateFlow()
    private val curSessions: Map<String, T>
        get() = _sessions.value

    private val _curSessionId = MutableStateFlow("")
    val curSessionId: String
        get() = _curSessionId.value

    val curSession: StateFlow<T?> = combine(
        _sessions,
        _curSessionId
    ) { sessions, sessionId ->
        sessions[sessionId]
    }.asState(sessionScope, null)
    val curMessages: List<IMMessage<*>>
        get() = curSession.value?.messages ?: emptyList()

    protected abstract val supportMessageHandlers: List<HandlerKey<*>>

    protected var messageNotifyListenJob: Job? = null

    open fun init() {
        initSession()
        initMessage()
    }

    protected open fun initSession() {
        var obtainedSessions = querySessions()
        if (obtainedSessions.isEmpty()) {
            obtainedSessions = listOf(createSession())
        }

        _sessions.value = obtainedSessions.associateBy { it.sessionId }
        _curSessionId.value = obtainedSessions.first().sessionId
    }

    protected abstract fun querySessions(): List<T>

    protected abstract fun createSession(): T

    protected open fun initMessage() {
        messageNotifyListenJob?.cancel()
        messageNotifyListenJob = IMCenter.messageNotify
            .filter { it.handlerKey in supportMessageHandlers }
            .collectIn(sessionScope) {
                Log.d(tag, "message notify: $it")
                addOrModifyMessage(it.message)
            }
    }

    open fun switchSession(sessionId: String) {
        _curSessionId.value = sessionId
    }

    open fun addOrModifyMessage(message: IMMessage<*>) {
        val sessionId = message.sessionId
        val targetSession = curSessions[sessionId] ?: return

        val targetMsgIndex = targetSession.messages.indexOfFirst { it.messageId == message.messageId }
        if (targetMsgIndex != -1) {
            targetSession.messages[targetMsgIndex] = message
        } else {
            targetSession.messages.add(message)
        }

        val newSession = targetSession.copySession().apply {
            lastActiveTime = System.currentTimeMillis()
        }
        _sessions.value = curSessions + Pair(sessionId, newSession)
    }

    @CallSuper
    open fun destroy() {
        messageNotifyListenJob?.cancel()
    }
}