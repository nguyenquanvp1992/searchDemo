package com.quannv.searchapplication.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ConnectivityInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val builder: Request.Builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        } catch (e: Exception) {
            throw e
        }
    }

}