package com.ai.integrator.network

import com.ai.integrator.core.framework.thread.AppExecutor
import com.ai.integrator.network.interceptor.HttpHeaderInterceptor
import com.ai.integrator.network.interceptor.HttpLogInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpServiceManager {
    private const val TAG = "HttpServiceManager"

    private const val BASE_URL = "https://api.siliconflow.cn/v1/"

    private val dispatcher by lazy { Dispatcher(AppExecutor.networkExecutor) }

    fun <T> getServiceApi(apiCls: Class<T>, tag: String = TAG): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildHttpClient(tag))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiCls)
    }

    private fun buildHttpClient(tag: String): OkHttpClient {
        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .addInterceptor(HttpHeaderInterceptor())
            .addInterceptor(HttpLogInterceptor(tag))
            .build()
    }
}