package com.ai.integrator.data.dialogue.datasource

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.protocol.DialogueReplyReq
import com.ai.integrator.data.dialogue.protocol.DialogueReplyResp
import com.ai.integrator.network.HttpServiceManager
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.stream.consumeAsFlow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Streaming

private interface DialogueDetailServiceApi {
    @POST("chat/completions")
    @Streaming
    suspend fun reqDialogueReply(
        @Body reqBody: DialogueReplyReq
    ): Response<ResponseBody>
}

class DialogueDetailRemoteDataSource {
    private val serviceApi by lazy {
        HttpServiceManager.getServiceApi(DialogueDetailServiceApi::class.java, TAG)
    }

    private val gson by lazy {
        GsonBuilder().disableHtmlEscaping().create()
    }

    suspend fun reqDialogueReply(
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
        val res = serviceApi.reqDialogueReply(reqBody)
        val body = res.body()
        if (!res.isSuccessful || body == null) {
            return flowOf(ResultOrIntError.Failure(res.code(), res.message()))
        }

        return body.byteStream().bufferedReader().lines().consumeAsFlow()
            .filter { it.isNotEmpty() }
            .map { it.removePrefix(STREAM_DATA_PREFIX).trim() }
            .map {
                if (it == STREAM_DATA_DONE_FLAG) return@map ResultOrIntError.Success("")

                try {
                    val content = gson.fromJson(it, DialogueReplyResp::class.java).choices[0].delta.content
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