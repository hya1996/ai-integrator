package com.ai.integrator.network.interceptor

import com.ai.integrator.core.framework.log.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpLogInterceptor(
    private val tag: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        Log.i(tag, "req: $req")

        val res = chain.proceed(req)
        Log.i(tag, "res: $res")

        return res
    }
}