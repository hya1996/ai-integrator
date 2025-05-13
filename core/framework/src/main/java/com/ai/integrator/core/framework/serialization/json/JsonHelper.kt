package com.ai.integrator.core.framework.serialization.json

import com.ai.integrator.core.framework.log.Log
import kotlinx.serialization.json.Json

object JsonHelper {
    const val TAG = "JsonHelper"

    val json: Json by lazy {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }

    inline fun <reified T> safeDecodeFromString(string: String?, defaultVal: T): T {
        if (string == null) {
            Log.e(TAG, "decode string is null, invalid")
            return defaultVal
        }

        return try {
            json.decodeFromString<T>(string)
        } catch (e: Exception) {
            Log.e(TAG, "decode string error: $e, string: $string")
            defaultVal
        }
    }

    inline fun <reified T> safeEncodeToString(value: T): String {
        return try {
            json.encodeToString(value)
        } catch (e: Exception) {
            Log.e(TAG, "encode string error: $e, value: $value")
            ""
        }
    }
}