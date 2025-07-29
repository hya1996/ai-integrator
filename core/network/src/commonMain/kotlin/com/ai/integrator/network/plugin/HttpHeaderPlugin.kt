package com.ai.integrator.network.plugin

import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import io.ktor.client.plugins.api.createClientPlugin
import org.koin.mp.KoinPlatform

private const val TAG = "HttpHeaderPlugin"

private var authorization = ""

val HttpHeaderPlugin = createClientPlugin(TAG) {
    onRequest { request, _ ->
        request.headers.apply {
            append("Authorization", authorization.ifEmpty { getAuthorization() })
            append("Content-Type", "application/json")
        }
    }
}

private suspend fun getAuthorization(): String {
    return (KoinPlatform.getKoin().get<ApiKeyPref>().getApiKey() ?: "").also { authorization = it }
}