package com.ai.integrator.im.message

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.isUser
import com.ai.integrator.user.uid.myUid

interface MessageContent

interface IMMessage<T : MessageContent> {
    val messageId: String
    val sessionId: String
    val sender: IMIdentity
    val receiver: IMIdentity
    val type: MessageType
    val content: T
    val timestamp: Long
    var status: MessageStatus
}

val IMMessage<*>.isSentByMe: Boolean
    get() = sender.isUser && sender.id == myUid.uid