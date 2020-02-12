package com.healios.dreams.data

import com.healios.dreams.data.api.DreaMSService
import com.healios.dreams.model.LoginData
import javax.inject.Singleton

@Singleton
interface LoginManager {
    suspend fun signin(phoneNumber: String)
    suspend fun verifyCode(phoneNumber: String, code: String)
}

class DefaultLoginManager constructor(private val loginService: DreaMSService) : LoginManager{

    override suspend fun signin(phoneNumber: String) {
        loginService.signin(LoginData(phoneNumber, null))
    }

    override suspend fun verifyCode(phoneNumber: String, code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}