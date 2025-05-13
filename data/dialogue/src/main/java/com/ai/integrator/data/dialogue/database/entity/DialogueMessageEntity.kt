package com.ai.integrator.data.dialogue.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ai.integrator.core.framework.serialization.json.JsonHelper
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.message.MessageStatus

@Entity(tableName = "dialogue_message")
data class DialogueMessageEntity(
    @PrimaryKey
    @ColumnInfo(name = "message_id")
    val messageId: String,

    @ColumnInfo(name = "session_id")
    val sessionId: String,

    @ColumnInfo(name = "sender")
    val sender: IMIdentity,

    @ColumnInfo(name = "receiver")
    val receiver: IMIdentity,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "status")
    val status: MessageStatus
)

fun DialogueMessageEntity.toDialogueMessage(): DialogueMessage {
    val parsedContent = JsonHelper.safeDecodeFromString(content, DialogueMessageContent(text = ""))
    return DialogueMessage(
        messageId = messageId,
        sessionId = sessionId,
        sender = sender,
        receiver = receiver,
        content = parsedContent,
        timestamp = timestamp,
        status = status
    )
}