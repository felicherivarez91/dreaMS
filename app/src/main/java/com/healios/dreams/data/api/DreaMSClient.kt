package com.healios.dreams.data.api

import com.google.gson.GsonBuilder
import com.healios.dreams.DreaMSApp
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.util.BaseURLHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class DreaMSClient(private val tokenProvider: TokenProvider,
                   private val usesAuthentication: Boolean = false) {

    private var baseURL = BaseURLHelper.DEV.raw

    val dreamMSService: DreaMSService by lazy {
         Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
             .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build().create(DreaMSService::class.java)
    }

    private val okHttpClient: OkHttpClient by lazy {

        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        if (tokenProvider.tokenExists() && usesAuthentication){
            builder.addInterceptor(TokenInterceptor(tokenProvider))
        }


        builder.build()
    }

    private val gsonConverter : Converter.Factory by lazy{
        GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create()
        )
    }


}