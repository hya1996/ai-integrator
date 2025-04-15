package com.ai.integrator.im.message.type

import com.ai.integrator.im.message.Message
import com.ai.integrator.im.message.MessageContent
import com.ai.integrator.im.message.MessageStatus
import com.ai.integrator.im.message.MessageType
import com.ai.integrator.im.message.TEXT_MESSAGE
import kotlinx.serialization.Serializable

@Serializable
data class TextMessageContent(
    val text: String
) : MessageContent

@Serializable
data class TextMessage(
    override val messageId: String,
    override val sessionId: String,
    override val senderId: Long,
    override val receiverId: Long,
    override val content: TextMessageContent,
    override val timestamp: Long,
    override var status: MessageStatus
) : Message<TextMessageContent> {
    override val type: MessageType = TEXT_MESSAGE
}