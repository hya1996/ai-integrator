package com.ai.integrator.network

import com.ai.integrator.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpServiceManager {
    private const val BASE_URL = "https://api.siliconflow.cn/v1/"

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    fun <T> getServiceApi(apiCls: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiCls)
    }
}