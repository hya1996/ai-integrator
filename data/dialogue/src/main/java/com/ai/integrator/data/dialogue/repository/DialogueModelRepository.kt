package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.data.dialogue.datasource.DialogueModelLocalDataSource
import com.ai.integrator.data.dialogue.model.DialogueModelInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DialogueModelRepository @Inject constructor(
    private val dialogueModelLocalDS: DialogueModelLocalDataSource
) {
    fun getModelList(): Flow<List<DialogueModelInfo>> = dialogueModelLocalDS.getModelList()

    suspend fun getModelById(id: Long): DialogueModelInfo? = dialogueModelLocalDS.getModelById(id)
}