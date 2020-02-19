package com.healios.dreams.data.api

import com.healios.dreams.data.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenProvider: TokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(HTTPHeaders.AUTHORIZATION, "Bearer " + tokenProvider.token)
            .build()

        return chain.proceed(newRequest)
    }
}