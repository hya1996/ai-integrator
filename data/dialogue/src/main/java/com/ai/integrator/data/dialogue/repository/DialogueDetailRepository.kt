package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.data.dialogue.datasource.DialogueDetailRemoteDataSource
import com.ai.integrator.data.dialogue.model.DialogueMessage
import kotlinx.coroutines.flow.Flow

class DialogueDetailRepository(
    private val dialogueDetailRemoteDS: DialogueDetailRemoteDataSource = DialogueDetailRemoteDataSource(),
) {
    suspend fun reqDialogueReply(
        modelName: String,
        messages: List<DialogueMessage>
    ): Flow<ResultOrIntError<String>> {
        return dialogueDetailRemoteDS.reqDialogueReply(modelName, messages)
    }
}