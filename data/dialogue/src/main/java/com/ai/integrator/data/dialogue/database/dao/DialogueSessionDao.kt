package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ai.integrator.data.dialogue.database.entity.DialogueSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogueSessionDao {
    @Query(value = "SELECT * FROM dialogue_session ORDER BY last_active_time DESC")
    fun getDialogueSessions(): Flow<List<DialogueSessionEntity>>

    @Query(value = "SELECT * FROM dialogue_session ORDER BY last_active_time DESC LIMIT 1")
    suspend fun getLatestDialogueSession(): DialogueSessionEntity?

    @Upsert
    suspend fun upsertDialogueSession(session: DialogueSessionEntity)
}