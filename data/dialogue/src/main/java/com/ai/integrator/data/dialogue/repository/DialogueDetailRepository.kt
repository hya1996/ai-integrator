package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.data.dialogue.datasource.DialogueDetailRemoteDataSource
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import kotlinx.coroutines.flow.Flow

class DialogueDetailRepository(
    private val dialogueModelRepo: DialogueModelRepository = DialogueModelRepository(),
    private val dialogueDetailRemoteDS: DialogueDetailRemoteDataSource = DialogueDetailRemoteDataSource()
) {
    suspend fun reqDialogueReply(
        modelId: Long,
        messages: List<DialogueMessageContent>
    ): Flow<ResultOrIntError<String>> {
        val modelName = dialogueModelRepo.getModelById(modelId)?.modelName ?: ""
        return dialogueDetailRemoteDS.reqDialogueReply(modelName, messages)
    }
}