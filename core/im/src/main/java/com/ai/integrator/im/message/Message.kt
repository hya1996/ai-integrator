package com.ai.integrator.im.message

interface MessageContent

interface Message<T : MessageContent> {
    val messageId: String
    val sessionId: String
    val senderId: Long
    val receiverId: Long
    val type: MessageType
    val content: T
    val timestamp: Long
    var status: MessageStatus
}