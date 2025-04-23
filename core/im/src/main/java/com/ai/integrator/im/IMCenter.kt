package com.ai.integrator.im

import com.ai.integrator.core.framework.flow.MutableEventFlow
import com.ai.integrator.core.framework.flow.asEventFlow
import com.ai.integrator.core.framework.flow.collectIn
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.core.framework.thread.AppDefaultThreadFactory
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.message.IMMessageHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

data class MessageNotifyInfo(
    val handlerKey: HandlerKey<*>,
    val message: IMMessage<*>
)

object IMCenter {
    private const val TAG = "IMCenter"

    private val _messageNotify = MutableEventFlow<MessageNotifyInfo>()
    val messageNotify = _messageNotify.asEventFlow()

    private val imDispatcher = Executors.newSingleThreadExecutor(
        AppDefaultThreadFactory("app-im-thread", 3)).asCoroutineDispatcher()
    private val imScope: CoroutineScope = CoroutineScope(imDispatcher + SupervisorJob())

    private val messageHandlerMap = ConcurrentHashMap<HandlerKey<*>, IMMessageHandler>()
    private val messageReceiveListenJobMap = ConcurrentHashMap<HandlerKey<*>, Job>()

    fun <T : IMMessageHandler> registerMessageHandler(key: HandlerKey<T>, handler: T) {
        messageHandlerMap[key] = handler

        messageReceiveListenJobMap[key]?.cancel()
        messageReceiveListenJobMap[key] = createMessageReceiveListenJob(key, handler)
    }

    fun unregisterMessageHandler(key: HandlerKey<*>) {
        messageHandlerMap.remove(key)

        messageReceiveListenJobMap[key]?.cancel()
        messageReceiveListenJobMap.remove(key)
    }

    private fun <T : IMMessageHandler> createMessageReceiveListenJob(key: HandlerKey<T>, handler: T): Job {
        return handler.receiveMessageNotify.collectIn(imScope) { message ->
            notifyNewMessage(key, message)
        }
    }

    suspend fun dispatchMessage(key: HandlerKey<*>, message: IMMessage<*>) {
        val handler = getMessageHandler(key)
        notifyNewMessage(key, message)
        handler.sendMessage(message)
    }

    suspend fun dispatchMessages(key: HandlerKey<*>, messages: List<IMMessage<*>>) {
        if (messages.isEmpty()) {
            Log.e(TAG, "message list is empty, key = $key")
            return
        }

        val handler = getMessageHandler(key)
        notifyNewMessage(key, messages.last())
        handler.sendMessages(messages)
    }

    private fun <T : IMMessageHandler> getMessageHandler(key: HandlerKey<T>): T {
        val handler = messageHandlerMap[key]
            ?: throw IllegalArgumentException("unknown key: $key")

        @Suppress("UNCHECKED_CAST")
        return handler as T
    }

    private fun notifyNewMessage(key: HandlerKey<*>, message: IMMessage<*>) {
        val notifyInfo = MessageNotifyInfo(
            handlerKey = key,
            message = message
        )
        _messageNotify.publish(notifyInfo)
    }
}