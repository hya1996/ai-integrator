package com.ai.integrator.network.plugin

import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import com.ai.integrator.network.HEADER_KEY_AUTHORIZATION
import com.ai.integrator.network.HEADER_KEY_CONTENT_TYPE
import io.ktor.client.plugins.api.createClientPlugin
import org.koin.mp.KoinPlatform

private const val TAG = "HttpHeaderPlugin"

private var authorization = ""

val HttpHeaderPlugin = createClientPlugin(TAG) {
    onRequest { request, _ ->
        request.headers.apply {
            if (!contains(HEADER_KEY_AUTHORIZATION)) {
                append(HEADER_KEY_AUTHORIZATION, authorization.ifEmpty { getAuthorization() })
            }
            if (!contains(HEADER_KEY_CONTENT_TYPE)) {
                append(HEADER_KEY_CONTENT_TYPE, "application/json")
            }
        }
    }
}

private suspend fun getAuthorization(): String {
    return (KoinPlatform.getKoin().get<ApiKeyPref>().getApiKey() ?: "").also { authorization = it }
}