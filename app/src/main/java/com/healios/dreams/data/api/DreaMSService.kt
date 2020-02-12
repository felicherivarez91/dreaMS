package com.healios.dreams.data.api

import com.healios.dreams.model.LoginData
import retrofit2.http.POST

interface DreaMSService {

    @POST("auth/signin")
    suspend fun signin(userData: LoginData)

    @POST("auth/signin")
    suspend fun verifyPhone(loginData: LoginData)




}