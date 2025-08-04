package com.ai.integrator.data.platform.datasource

import com.ai.integrator.core.framework.common.ResultOrIntError
import com.ai.integrator.network.HEADER_KEY_AUTHORIZATION
import com.ai.integrator.network.HttpServiceManager
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

interface PlatformInfoServiceApi {
    @GET("user/info")
    suspend fun getUserInfo(
        @Header(HEADER_KEY_AUTHORIZATION) apiKey: String
    ): HttpResponse
}

class PlatformInfoRemoteDataSource {
    private val serviceApi by lazy {
        HttpServiceManager.getHttpService(TAG).createPlatformInfoServiceApi()
    }

    suspend fun checkApiKeyIsValid(apiKey: String): ResultOrIntError<Unit> {
        val res = serviceApi.getUserInfo(apiKey)
        return if (res.status == HttpStatusCode.OK) {
            ResultOrIntError.Success(Unit)
        } else {
            ResultOrIntError.Failure(res.status.value, "")
        }
    }

    companion object {
        private const val TAG = "PlatformInfoRemoteDataSource"
    }
}