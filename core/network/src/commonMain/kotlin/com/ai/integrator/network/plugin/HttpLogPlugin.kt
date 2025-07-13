package com.ai.integrator.network.plugin

import com.ai.integrator.core.framework.log.Log
import io.ktor.client.plugins.api.SendingRequest
import io.ktor.client.plugins.api.createClientPlugin

private const val TAG = "HttpLogPlugin"

class HttpLogPluginConfig {
    var tag: String = TAG
}

val HttpLogPlugin = createClientPlugin(TAG, ::HttpLogPluginConfig) {
    val tag = pluginConfig.tag

    on(SendingRequest) { request, _ ->
        Log.i(tag, "req: $request")
    }

    onResponse { response ->
        Log.i(tag, "res: $response")
    }
}