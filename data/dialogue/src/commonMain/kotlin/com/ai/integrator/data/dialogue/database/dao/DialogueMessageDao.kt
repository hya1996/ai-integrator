package com.ai.integrator.data.dialogue.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ai.integrator.data.dialogue.database.entity.DialogueMessageEntity
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.user.uid.myUid
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogueMessageDao {
    @Query(value = """
        SELECT * FROM dialogue_message 
        WHERE session_id = :sessionId
        ORDER BY timestamp DESC
    """)
    fun getDialogueMessagesBySessionId(sessionId: String): Flow<List<DialogueMessageEntity>>

    @Query(value = """
        SELECT * FROM dialogue_message
        WHERE session_id = :sessionId
        AND sender = :sender
        ORDER BY timestamp DESC
        LIMIT 1
    """)
    suspend fun getLastDialogueMessageSentByMe(
        sessionId: String,
        sender: IMIdentity = IMIdentity(myUid.uid, IdentityType.USER)
    ): DialogueMessageEntity?

    @Upsert
    suspend fun upsertDialogueMessage(message: DialogueMessageEntity)
}