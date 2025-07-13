package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ai.integrator.data.dialogue.database.entity.DialogueMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogueMessageDao {
    @Query(value = """
        SELECT * FROM dialogue_message 
        WHERE session_id = :sessionId
        ORDER BY timestamp
    """)
    fun getDialogueMessagesBySessionId(sessionId: String): Flow<List<DialogueMessageEntity>>

    @Upsert
    suspend fun upsertDialogueMessage(message: DialogueMessageEntity)
}