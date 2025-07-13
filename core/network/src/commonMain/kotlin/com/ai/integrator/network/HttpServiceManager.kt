package com.ai.integrator.network

import com.ai.integrator.core.framework.serialization.json.JsonHelper
import com.ai.integrator.network.plugin.HttpHeaderPlugin
import com.ai.integrator.network.plugin.HttpLogPlugin
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object HttpServiceManager {
    private const val TAG = "HttpServiceManager"

    private const val BASE_URL = "https://api.siliconflow.cn/v1/"

    fun getHttpService(tag: String = TAG): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(buildHttpClient(tag))
            .baseUrl(BASE_URL)
            .build()
    }

    private fun buildHttpClient(tag: String): HttpClient {
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