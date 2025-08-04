package com.ai.integrator.data.platform.repository

import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import com.ai.integrator.data.platform.datasource.PlatformModelLocalDataSource
import com.ai.integrator.data.platform.model.PlatformModelInfo
import kotlinx.coroutines.flow.Flow

class PlatformModelRepository(
    private val apiKeyPref: ApiKeyPref,
    private val platformModelLocalDS: PlatformModelLocalDataSource
) {
    val apiKeyFlow: Flow<String> get() = apiKeyPref.listenApiKey()

    fun updateApiKey(apiKey: String) {
        apiKeyPref.setApiKey(apiKey)
    }

    fun getModelList(): Flow<List<PlatformModelInfo>> = platformModelLocalDS.getModelList()

    suspend fun getModelById(id: Long): PlatformModelInfo? = platformModelLocalDS.getModelById(id)
}