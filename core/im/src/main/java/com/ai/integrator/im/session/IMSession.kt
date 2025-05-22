package com.ai.integrator.im.session

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.message.IMMessage

interface IMSession<T : IMSession<T>> {
    val sessionId: String
    val participants: List<IMIdentity>
    val messages: MutableList<IMMessage<*>>
    var lastActiveTime: Long

    fun copySession(): T
}