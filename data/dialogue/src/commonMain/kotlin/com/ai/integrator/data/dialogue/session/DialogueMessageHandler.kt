@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package com.ai.integrator.data.dialogue.session

import com.ai.integrator.core.framework.common.onFailure
import com.ai.integrator.core.framework.common.onSuccess
import com.ai.integrator.core.framework.flow.MutableEventFlow
import com.ai.integrator.core.framework.flow.asEventFlow
import com.ai.integrator.core.framework.flow.collectIn
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.data.dialogue.model.DIALOGUE_ROLE_ASSISTANT
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.repository.DialogueDetailRepository
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.message.IMMessageHandler
import com.ai.integrator.im.message.MessageStatus
import kotlinx.coroutines.CoroutineScope
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private const val TAG = "DialogueMessageHandler"

class DialogueMessageHandler(
    private val scope: CoroutineScope,
    private val dialogueDetailRepo: DialogueDetailRepository
) : IMMessageHandler {
    object Key : HandlerKey<DialogueMessageHandler>

    private val _receiveMessageNotify = MutableEventFlow<IMMessage<*>>()
    override val receiveMessageNotify = _receiveMessageNotify.asEventFlow()

    override suspend fun sendMessages(messages: List<IMMessage<*>>) {
        if (messages.isEmpty()) return

        when (messages.first()) {
            // todo opt transform logic
            is DialogueMessage -> handleDialogueMessages(messages.map { it as DialogueMessage })
            else -> {
                Log.e(TAG, "unsupported message type: ${messages.first().type}")
            }
        }
    }

    private suspend fun handleDialogueMessages(messages: List<DialogueMessage>) {
        if (messages.isEmpty()) {
            Log.e(TAG, "handleDialogueMessages messages is empty")
            return
        }

        val lastMsg = messages.last()
        dialogueDetailRepo.upsertDialogueMessage(lastMsg)

        val receiver = lastMsg.receiver
        if (receiver.type != IdentityType.AI) {
            Log.e(TAG, "handleDialogueMessages receiver message object not AI model")
            return
        }

        var replyContent = DialogueMessageContent(DIALOGUE_ROLE_ASSISTANT, "")
        var replyMsg = DialogueMessage(
            messageId = Uuid.random().toString(),
            sessionId = lastMsg.sessionId,
            sender = lastMsg.receiver,
            receiver = lastMsg.sender,
            content = replyContent,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            status = MessageStatus.NO_STATUS
        )
        dialogueDetailRepo.reqDialogueReply(
            modelId = receiver.id,
            messages = messages.map { it.content }
        ).collectIn(scope) {
            it.onSuccess { content ->
                replyContent = replyContent.copy(text = replyContent.text + content)
                replyMsg = replyMsg.copy(content = replyContent)
                dialogueDetailRepo.upsertDialogueMessage(replyMsg)
            }.onFailure { code, message ->
                Log.e(TAG, "handleDialogueMessages fail code: $code, message: $message")
            }
        }
    }
}