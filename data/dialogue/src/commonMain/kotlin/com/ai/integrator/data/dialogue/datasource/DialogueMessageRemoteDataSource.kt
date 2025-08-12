package com.ai.integrator.data.dialogue.datasource

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.core.framework.serialization.json.JsonHelper
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.protocol.DialogueReplyReq
import com.ai.integrator.data.dialogue.protocol.DialogueReplyResp
import com.ai.integrator.network.HttpServiceManager
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Streaming
import io.ktor.client.call.body
import io.ktor.client.statement.HttpStatement
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.exhausted
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface DialogueMessageServiceApi {
    @POST("chat/completions")
    @Streaming
    suspend fun reqDialogueReply(
        @Body reqBody: DialogueReplyReq
    ): HttpStatement
}

class DialogueMessageRemoteDataSource {
    private val serviceApi by lazy {
        HttpServiceManager.getHttpService(TAG).createDialogueMessageServiceApi()
    }

    fun reqDialogueReply(
        modelName: String,
        messages: List<DialogueMessageContent>
    ): Flow<ResultOrIntError<String>> {
        if (modelName.isEmpty()) {
            return flowOf(ResultOrIntError.Failure(ERROR_CODE_MODEL_ARG_INVALID, "model name is invalid"))
        }

        val reqBody = DialogueReplyReq(
            model = modelName,
            messages = messages
        )
        return flow {
            // todo add fail handle
            serviceApi.reqDialogueReply(reqBody).execute { res ->
                val channel: ByteReadChannel = res.body()
                while (!channel.exhausted()) {
                    emit(channel.readUTF8Line())
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