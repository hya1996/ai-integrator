package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.data.dialogue.database.dao.DialogueSessionDao
import com.ai.integrator.data.dialogue.database.entity.toDialogueSession
import com.ai.integrator.data.dialogue.database.entity.toDialogueSessionEntity
import com.ai.integrator.data.dialogue.model.DialogueSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DialogueSessionRepository(
    private val sessionDao: DialogueSessionDao
) {
    fun getDialogueSessionsByModelId(modelId: Long): Flow<List<DialogueSession>> {
        return sessionDao.getDialogueSessions(modelId)
            .map { it.map { session -> session.toDialogueSession() } }
    }

    suspend fun getLatestDialogueSessionByModelId(modelId: Long): DialogueSession? {
        return sessionDao.getLatestDialogueSession(modelId)?.toDialogueSession()
    }

    suspend fun upsertDialogueSession(session: DialogueSession) {
        sessionDao.upsertDialogueSession(session.toDialogueSessionEntity())
    }
}