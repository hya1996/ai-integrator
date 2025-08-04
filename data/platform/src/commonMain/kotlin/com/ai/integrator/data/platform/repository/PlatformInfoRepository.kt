package com.ai.integrator.data.platform.repository

import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.data.platform.datasource.PlatformInfoRemoteDataSource
import kotlinx.coroutines.flow.Flow

class PlatformInfoRepository(
    private val apiKeyPref: ApiKeyPref,
    private val platformInfoRemoteDS: PlatformInfoRemoteDataSource
) {
    val apiKeyFlow: Flow<String> get() = apiKeyPref.listenApiKey()

    suspend fun updateApiKey(apiKey: String) {
        apiKeyPref.setApiKey(apiKey)
    }

    suspend fun checkApiKeyIsValid(apiKey: String): ResultOrIntError<Unit> {
        return platformInfoRemoteDS.checkApiKeyIsValid(apiKey)
    }
}