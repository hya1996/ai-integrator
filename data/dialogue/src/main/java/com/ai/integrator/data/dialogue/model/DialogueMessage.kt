package com.ai.integrator.data.dialogue.model

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.message.DIALOGUE_MESSAGE
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.message.MessageContent
import com.ai.integrator.im.message.MessageStatus
import com.ai.integrator.im.message.MessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val DIALOGUE_ROLE_USER = "user"
const val DIALOGUE_ROLE_ASSISTANT = "assistant"
const val DIALOGUE_ROLE_SYSTEM = "system"

@Serializable
data class DialogueMessageContent(
    @SerialName("role")
    val role: String = "",

    @SerialName("content")
    val text: String
) : MessageContent

@Serializable
data class DialogueMessage(
    override val messageId: String,
    override val sessionId: String,
    override val sender: IMIdentity,
    override val receiver: IMIdentity,
    override val content: DialogueMessageContent,
    override val timestamp: Long,
    override var status: MessageStatus
) : IMMessage<DialogueMessageContent> {
    override val type: MessageType = DIALOGUE_MESSAGE
}
