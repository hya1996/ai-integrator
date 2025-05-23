package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ai.integrator.data.dialogue.database.entity.DialogueSessionEntity

@Dao
interface DialogueSessionDao {
    @Query(value = "SELECT * FROM dialogue_session ORDER BY last_active_time DESC")
    suspend fun getDialogueSessions(): List<DialogueSessionEntity>

    @Query(value = "SELECT * FROM dialogue_session ORDER BY last_active_time DESC LIMIT 1")
    suspend fun getLatestDialogueSession(): DialogueSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDialogueSession(session: DialogueSessionEntity)
}