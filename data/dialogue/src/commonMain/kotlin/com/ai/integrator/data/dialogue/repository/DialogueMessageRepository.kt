package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.entity.toDialogueMessage
import com.ai.integrator.data.dialogue.database.entity.toDialogueMessageEntity
import com.ai.integrator.data.dialogue.datasource.DialogueMessageRemoteDataSource
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.platform.repository.PlatformModelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DialogueMessageRepository(
    private val messageDao: DialogueMessageDao,
    private val platformModelRepo: PlatformModelRepository,
    private val dialogueMessageRemoteDS: DialogueMessageRemoteDataSource,
) {
    fun getDialogueMessagesBySessionId(sessionId: String): Flow<List<DialogueMessage>> {
        return messageDao.getDialogueMessagesBySessionId(sessionId)
            .map { it.map { message -> message.toDialogueMessage() } }
    }

    suspend fun getLastDialogueMessageSentByMe(sessionId: String): DialogueMessage? {
        return messageDao.getLastDialogueMessageSentByMe(sessionId)?.toDialogueMessage()
    }

    suspend fun upsertDialogueMessage(message: DialogueMessage) {
        messageDao.upsertDialogueMessage(message.toDialogueMessageEntity())
    }

    suspend fun reqDialogueReply(
        modelId: Long,
        messages: List<DialogueMessageContent>
    ): Flow<ResultOrIntError<String>> {
        val modelName = platformModelRepo.getModelById(modelId)?.modelName ?: ""
        return dialogueMessageRemoteDS.reqDialogueReply(modelName, messages)
    }
}