package com.ai.integrator.data.dialogue.datasource

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.core.framework.serialization.json.JsonHelper
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.protocol.DialogueReplyReq
import com.ai.integrator.data.dialogue.protocol.DialogueReplyResp
import com.ai.integrator.data.model.model.ModelInfo
import com.ai.integrator.network.HttpServiceManager
import io.ktor.client.request.header
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.exhausted
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DialogueMessageRemoteDataSource {
    private val httpClient by lazy {
        HttpServiceManager.buildHttpClient(TAG)
    }

    fun reqDialogueReply(
        modelInfo: ModelInfo?,
        messages: List<DialogueMessageContent>
    ): Flow<ResultOrIntError<String>> {
        if (modelInfo == null) {
            return flowOf(ResultOrIntError.Failure(ERROR_CODE_MODEL_ARG_INVALID, "model info is invalid"))
        }

        return channelFlow {
            // todo add fail handle
            httpClient.preparePost(modelInfo.requestUrl) {
                header(HttpHeaders.Authorization, modelInfo.apiKey)
                setBody(
                    DialogueReplyReq(
                        model = modelInfo.modelName,
                        messages = messages
                    )
                )
            }.execute { resp ->
                val channel = resp.bodyAsChannel()
                while (!channel.exhausted()) {
                    send(channel.readUTF8Line())
                }
            }
        }
            .filterNotNull()
            .filter { it.isNotEmpty() }
            .map { it.removePrefix(STREAM_DATA_PREFIX).trim() }
            .map {
                if (it == STREAM_DATA_DONE_FLAG) return@map ResultOrIntError.Success("")

                try {
                    val content = JsonHelper.json.decodeFromString<DialogueReplyResp>(it).choices[0].delta.text
                    ResultOrIntError.Success(content)
                } catch (e: Exception) {
                    Log.e(TAG, "parse reply error: ${e.message}")
                    ResultOrIntError.Failure(ERROR_CODE_PARSE_JSON, e.message ?: "")
                }
            }
            .flowOn(AppDispatcher.Network)
    }

    companion object {
        private const val TAG = "DialogueDetailRemoteDataSource"

        private const val STREAM_DATA_PREFIX = "data: "
        private const val STREAM_DATA_DONE_FLAG = "[DONE]"

        private const val ERROR_CODE_PARSE_JSON = 1000
        private const val ERROR_CODE_MODEL_ARG_INVALID = 1001
    }
}