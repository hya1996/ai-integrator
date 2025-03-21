package com.ai.integrator.data.dialogue.datasource

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.protocol.DialogueReplyReq
import com.ai.integrator.data.dialogue.protocol.DialogueReplyResp
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.stream.consumeAsFlow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Streaming

private interface DialogueDetailServiceApi {
    @Headers(
        "Content-Type: application/json"
    )
    @POST("chat/completions")
    @Streaming
    suspend fun reqDialogueReply(
        @Body reqBody: DialogueReplyReq
    ): Response<ResponseBody>
}

class DialogueDetailRemoteDataSource {
    private val serviceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DialogueDetailServiceApi::class.java)
    }

    private val gson by lazy {
        GsonBuilder().disableHtmlEscaping().create()
    }

    suspend fun reqDialogueReply(
        modelName: String,
        messages: List<DialogueMessage>
    ): Flow<ResultOrIntError<String>> {
        val reqBody = DialogueReplyReq(
            model = modelName,
            messages = messages
        )
        val res = serviceApi.reqDialogueReply(reqBody)
        Log.d(TAG, "reqDialogueReply code: ${res.code()}, message: ${res.message()}")

        val body = res.body()
        if (!res.isSuccessful || body == null) {
            return flowOf(ResultOrIntError.Failure(res.code(), res.message()))
        }

        return body.byteStream().bufferedReader().lines().consumeAsFlow()
            .filter { it.isNotEmpty() }
            .map { it.removePrefix("data: ").trim() }
            .map {
                if (it == "[DONE]") return@map ResultOrIntError.Success("")

                try {
                    val content = gson.fromJson(it, DialogueReplyResp::class.java).choices[0].delta.content
                    ResultOrIntError.Success(content)
                } catch (e: Exception) {
                    Log.e(TAG, "parse reply error: ${e.message}")
                    ResultOrIntError.Failure(ERROR_CODE_PARSE_JSON, e.message ?: "")
                }
            }
    }

    companion object {
        private const val TAG = "DialogueDetailRemoteDataSource"

        private const val BASE_URL = "https://api.siliconflow.cn/v1/"

        private const val ERROR_CODE_PARSE_JSON = 1000
    }
}