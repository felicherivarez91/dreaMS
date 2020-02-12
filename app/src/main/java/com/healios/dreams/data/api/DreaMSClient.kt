package com.healios.dreams.data.api

import com.google.gson.GsonBuilder
import com.healios.dreams.util.BaseURLHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class DreaMSClient {

    private var baseURL = BaseURLHelper.LOCAL.raw

    val dreamMSService: DreaMSService by lazy {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(gsonConverter)

        builder.build().create(DreaMSService::class.java)
    }

    private val okHttpClient: OkHttpClient by lazy {

        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        /*
        if(authenticator != null){
            builder.addInterceptor(authenticator)
            builder.authenticator(authenticator)
        }
        */

        builder.build()
    }

    private val gsonConverter : Converter.Factory by lazy{
        GsonConverterFactory.create(
            GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        )
    }


}