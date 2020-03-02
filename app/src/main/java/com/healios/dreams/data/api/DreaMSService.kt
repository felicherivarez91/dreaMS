package com.healios.dreams.data.api

import com.healios.dreams.model.*
import retrofit2.http.*

interface DreaMSService {

    @POST("auth/signin")
    fun signin(@Body userRequest: LoginRequest): ApiResponse<Unit>

    @POST("auth/signin")
    fun verifyPhone(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>

    @POST("auth/signin")
    fun resendCode(@Body loginRequest: LoginRequest): ApiResponse<Unit>

    @POST("auth/signup")
    fun checkUniqueNickname(@Body uniqueNicknameParams: UniqueNicknameRequest) : ApiResponse<Unit>

    @POST("auth/signup")
    fun completeAccountDetails(@Body accountDetails: AccounDetailsRequest) : ApiResponse<Unit>

    //region: User Info
    @GET("users/{userId}")
    fun getUserCollectionById(@Path("userId") userId: String, @Query("type") type: String, @Query("studyPrefix") studyPrefix: String ): ApiResponse<UserCollectionDataResponse>
    //endregion


}