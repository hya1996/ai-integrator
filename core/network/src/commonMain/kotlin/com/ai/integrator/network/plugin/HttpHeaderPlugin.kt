package com.ai.integrator.network.plugin

import com.ai.integrator.core.framework.log.Log
import io.ktor.client.plugins.api.createClientPlugin

private const val TAG = "HttpHeaderPlugin"

val HttpHeaderPlugin = createClientPlugin(TAG) {
    onRequest { request, _ ->
        request.headers.apply {
            append("Authorization", getAuthorization())
            append("Content-Type", "application/json")
        }
    }
}

private fun getAuthorization(): String {
    return try {
        ""
//        val inputStream = Application.resources.openRawResource(R.raw.api_key)
//        inputStream.bufferedReader().readText().trim()
    } catch (e: Exception) {
        Log.e(TAG, "read api key error: ${e.message}")
        ""
    }
}