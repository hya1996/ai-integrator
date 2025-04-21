package com.ai.integrator.im.session

import androidx.annotation.CallSuper
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.flow.collectIn
import com.ai.integrator.im.IMCenter
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter

abstract class IMSessionController(
    private val peerId: Long,
    private val sessionScope: CoroutineScope = CoroutineScope(AppDispatcher.Background + SupervisorJob())
) {
    private val _sessions = MutableStateFlow<Map<String, IMSession>>(emptyMap())
    val sessions = _sessions.asStateFlow()
    private val curSessions get() = _sessions.value

    private val curSessionId = MutableStateFlow("")
    val curSession: StateFlow<IMSession?> = combine(
        _sessions,
        curSessionId
    ) { sessions, sessionId ->
        sessions[sessionId]
    }.asState(sessionScope, null)

    protected abstract val supportMessageHandlers: List<HandlerKey<*>>

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
        curSessionId.value = obtainedSessions.first().sessionId
    }

    protected abstract fun querySessions(): List<IMSession>

    protected abstract fun createSession(): IMSession

    protected open fun initMessage() {
        IMCenter.messageNotify
            .filter { it.handlerKey in supportMessageHandlers }
            .collectIn(sessionScope) {
                addMessage(it.message)
            }
    }

    open fun switchSession(sessionId: String) {
        curSessionId.value = sessionId
    }

    open fun addMessage(message: IMMessage<*>) {
        val sessionId = message.sessionId
        val targetSession = curSessions[sessionId] ?: return

        targetSession.messages.add(message)
        targetSession.lastActiveTime = System.currentTimeMillis()
        _sessions.value = curSessions + Pair(sessionId, targetSession)
    }

    @CallSuper
    open fun destroy() {
        sessionScope.coroutineContext.cancelChildren()
    }
}