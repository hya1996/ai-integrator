package com.ai.integrator.data.dialogue.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ai.integrator.data.dialogue.model.DialogueSession
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType

@Entity(tableName = "dialogue_session")
data class DialogueSessionEntity(
    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: String,

    @ColumnInfo(name = "model_id")
    val modelId: Long,

    @ColumnInfo(name = "participants")
    val participants: List<IMIdentity>,

    @ColumnInfo(name = "last_active_ts")
    val lastActiveTs: Long
)

fun DialogueSessionEntity.toDialogueSession(): DialogueSession {
    return DialogueSession(
        sessionId = sessionId,
        participants = participants,
        lastActiveTs = lastActiveTs
    )
}

fun DialogueSession.toDialogueSessionEntity(): DialogueSessionEntity {
    val modelId = participants.find { it.type == IdentityType.AI }?.id
        ?: throw IllegalArgumentException("model id must need")
    return DialogueSessionEntity(
        sessionId = sessionId,
        modelId = modelId,
        participants = participants,
        lastActiveTs = lastActiveTs
    )
}