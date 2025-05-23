package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ai.integrator.data.dialogue.database.entity.DialogueMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogueMessageDao {
    @Query(value = """
        SELECT * FROM dialogue_message 
        WHERE session_id = :sessionId
        ORDER BY timestamp
    """)
    suspend fun getDialogueMessagesBySessionId(sessionId: String): List<DialogueMessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDialogueMessage(message: DialogueMessageEntity)
}