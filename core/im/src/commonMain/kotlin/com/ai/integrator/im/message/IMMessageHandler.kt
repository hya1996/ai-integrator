package com.ai.integrator.im.message

import kotlinx.coroutines.flow.Flow

interface HandlerKey<T : IMMessageHandler>

interface IMMessageHandler {
    val receiveMessageNotify: Flow<IMMessage<*>>

    suspend fun sendMessage(message: IMMessage<*>) {}

    suspend fun sendMessages(messages: List<IMMessage<*>>) {}
}