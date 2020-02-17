package com.healios.dreams.data

import com.healios.dreams.data.api.ApiResponse
import com.healios.dreams.data.api.DreaMSService
import com.healios.dreams.model.LoginRequest
import javax.inject.Singleton

@Singleton
interface LoginManager {
    fun signin(phoneNumber: String) : ApiResponse<Unit>
    fun verifyCode(phoneNumber: String, code: String)
}

class DefaultLoginManager constructor(private val loginService: DreaMSService) : LoginManager{

    override fun signin(phoneNumber: String) : ApiResponse<Unit> =
        loginService.signin(LoginRequest(phoneNumber, null))


    override fun verifyCode(phoneNumber: String, code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}