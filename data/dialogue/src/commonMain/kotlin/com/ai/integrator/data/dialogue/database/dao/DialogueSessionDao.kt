package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ai.integrator.data.dialogue.database.entity.DialogueSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogueSessionDao {
    @Query(value = """
        SELECT * FROM dialogue_session
        WHERE model_id = :modelId
        ORDER BY last_active_ts DESC
    """)
    fun getDialogueSessions(modelId: Long): Flow<List<DialogueSessionEntity>>

    @Query(value = """
        SELECT * FROM dialogue_session 
        WHERE model_id = :modelId
        ORDER BY last_active_ts DESC 
        LIMIT 1
    """)
    suspend fun getLatestDialogueSession(modelId: Long): DialogueSessionEntity?

    @Upsert
    suspend fun upsertDialogueSession(session: DialogueSessionEntity)
}