package com.healios.dreams.data.api

import com.healios.dreams.model.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DreaMSService {

    @POST("auth/signin")
    fun signin(@Body userRequest: LoginRequest) : ApiResponse<Unit>

    @POST("auth/signin")
    fun verifyPhone(@Body loginRequest: LoginRequest)




}