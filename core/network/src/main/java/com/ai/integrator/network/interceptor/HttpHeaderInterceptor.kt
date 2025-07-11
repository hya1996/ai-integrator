package com.ai.integrator.network.interceptor

import com.ai.integrator.core.framework.log.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newReq = chain.request().newBuilder()
            .addHeader("Authorization", getAuthorization())
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(newReq)
    }

    private fun getAuthorization(): String {
        return try {
            ""
//            val inputStream = Application.resources.openRawResource(R.raw.api_key)
//            inputStream.bufferedReader().readText().trim()
        } catch (e: Exception) {
            Log.e(TAG, "read api key error: ${e.message}")
            ""
        }
    }

    companion object {
        private const val TAG = "HeaderInterceptor"
    }
}