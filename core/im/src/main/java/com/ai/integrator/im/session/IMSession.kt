package com.ai.integrator.im.session

import com.ai.integrator.im.message.IMMessage

data class IMSession(
    val sessionId: String,
    val participantIds: List<Long>,
    val messages: MutableList<IMMessage<*>>,
    var lastActiveTime: Long = System.currentTimeMillis()
)