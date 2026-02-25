package com.ai.integrator.network

import com.ai.integrator.core.framework.serialization.json.JsonHelper
import com.ai.integrator.network.plugin.HttpHeaderPlugin
import com.ai.integrator.network.plugin.HttpLogPlugin
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object HttpServiceManager {
    private const val TAG = "HttpServiceManager"

    fun buildHttpClient(tag: String = TAG): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(JsonHelper.json)
            }
            install(HttpHeaderPlugin)
            install(HttpLogPlugin) {
                this.tag = tag
            }
        }
    }
}