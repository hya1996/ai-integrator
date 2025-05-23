package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.dao.DialogueSessionDao
import com.ai.integrator.data.dialogue.database.entity.toDialogueMessage
import com.ai.integrator.data.dialogue.database.entity.toDialogueMessageEntity
import com.ai.integrator.data.dialogue.database.entity.toDialogueSession
import com.ai.integrator.data.dialogue.database.entity.toDialogueSessionEntity
import com.ai.integrator.data.dialogue.datasource.DialogueDetailRemoteDataSource
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.model.DialogueSession
import kotlinx.coroutines.flow.Flow

class DialogueDetailRepository(
    private val sessionDao: DialogueSessionDao,
    private val messageDao: DialogueMessageDao,
    private val dialogueModelRepo: DialogueModelRepository,
    private val dialogueDetailRemoteDS: DialogueDetailRemoteDataSource,
) {
    suspend fun getDialogueSessionsByModelId(modelId: Long): List<DialogueSession> {
        return sessionDao.getDialogueSessions()
            .map { session -> session.toDialogueSession() }
    }

    suspend fun getLatestDialogueSessionByModelId(modelId: Long): DialogueSession? {
        return sessionDao.getLatestDialogueSession()?.toDialogueSession()
    }

    suspend fun insertDialogueSession(session: DialogueSession) {
        sessionDao.insertDialogueSession(session.toDialogueSessionEntity())
    }

    suspend fun getDialogueMessagesBySessionId(sessionId: String): List<DialogueMessage> {
        return messageDao.getDialogueMessagesBySessionId(sessionId)
            .map { message -> message.toDialogueMessage() }
    }

    suspend fun insertDialogueMessage(message: DialogueMessage) {
        messageDao.insertDialogueMessage(message.toDialogueMessageEntity())
    }

    suspend fun reqDialogueReply(
        modelId: Long,
        messages: List<DialogueMessageContent>
    ): Flow<ResultOrIntError<String>> {
        val modelName = dialogueModelRepo.getModelById(modelId)?.modelName ?: ""
        return dialogueDetailRemoteDS.reqDialogueReply(modelName, messages)
    }
}