package com.ai.integrator.network.plugin

import com.ai.integrator.network.HEADER_KEY_CONTENT_TYPE
import io.ktor.client.plugins.api.createClientPlugin

private const val TAG = "HttpHeaderPlugin"

val HttpHeaderPlugin = createClientPlugin(TAG) {
    onRequest { request, _ ->
        request.headers.apply {
            if (!contains(HEADER_KEY_CONTENT_TYPE)) {
                append(HEADER_KEY_CONTENT_TYPE, "application/json")
            }
        }
    }
}