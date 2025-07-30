package com.ai.integrator.data.dialogue.repository

import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import com.ai.integrator.data.dialogue.datasource.DialogueModelLocalDataSource
import com.ai.integrator.data.dialogue.model.DialogueModelInfo
import kotlinx.coroutines.flow.Flow

class DialogueModelRepository(
    private val apiKeyPref: ApiKeyPref,
    private val dialogueModelLocalDS: DialogueModelLocalDataSource
) {
    val apiKeyFlow: Flow<String> get() = apiKeyPref.listenApiKey()

    fun updateApiKey(apiKey: String) {
        apiKeyPref.setApiKey(apiKey)
    }

    fun getModelList(): Flow<List<DialogueModelInfo>> = dialogueModelLocalDS.getModelList()

    suspend fun getModelById(id: Long): DialogueModelInfo? = dialogueModelLocalDS.getModelById(id)
}