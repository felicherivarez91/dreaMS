package com.healios.dreams.data

import com.healios.dreams.data.api.ApiResponse
import com.healios.dreams.data.api.DreaMSService
import com.healios.dreams.model.AccounDetailsRequest
import com.healios.dreams.model.LoginRequest
import com.healios.dreams.model.LoginResponse
import retrofit2.http.Body
import javax.inject.Singleton
import kotlin.math.log

@Singleton
interface LoginManager {
    fun signin(phoneNumber: String): ApiResponse<Unit>
    fun verifyCode(phoneNumber: String, code: String): ApiResponse<LoginResponse>
}

class DefaultLoginManager constructor(private val loginService: DreaMSService) : LoginManager {


    override fun signin(phoneNumber: String): ApiResponse<Unit> =
        loginService.signin(LoginRequest(phoneNumber, null))


    override fun verifyCode(phoneNumber: String, code: String): ApiResponse<LoginResponse> =
        loginService.verifyPhone(LoginRequest(phoneNumber, code))


}