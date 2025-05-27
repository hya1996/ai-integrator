package com.ai.integrator.data.dialogue.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ai.integrator.data.dialogue.model.DialogueSession
import com.ai.integrator.im.identity.IMIdentity

@Entity(tableName = "dialogue_session",)
data class DialogueSessionEntity(
    @PrimaryKey
    @ColumnInfo(name = "session_id")
    val sessionId: String,

    @ColumnInfo(name = "participants")
    val participants: List<IMIdentity>,

    @ColumnInfo(name = "last_active_time")
    val lastActiveTime: Long
)

fun DialogueSessionEntity.toDialogueSession(): DialogueSession {
    return DialogueSession(
        sessionId = sessionId,
        participants = participants,
        lastActiveTime = lastActiveTime
    )
}

fun DialogueSession.toDialogueSessionEntity(): DialogueSessionEntity {
    return DialogueSessionEntity(
        sessionId = sessionId,
        participants = participants,
        lastActiveTime = lastActiveTime
    )
}