package com.ai.integrator.core.framework.serialization.json

import kotlinx.serialization.json.Json

object JsonHelper {
    val json: Json by lazy {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }
}