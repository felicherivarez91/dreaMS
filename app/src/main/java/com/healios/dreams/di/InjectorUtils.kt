package com.healios.dreams.di

import com.healios.dreams.data.*
import com.healios.dreams.data.api.DreaMSClient
import com.healios.dreams.data.api.TokenInterceptor

object InjectorUtils {

    fun  getLoginManager(): LoginManager {
        return DefaultLoginManager(DreaMSClient(getTokenProvider(),
            false).dreamMSService)
    }

    fun getAccountInformationManager(): AccountInformationManager {
        return DefaultAccountInformationManager(DreaMSClient(getTokenProvider(),
            true).dreamMSService)
    }

    fun getTokenProvider(): TokenProvider {
        return PreferencesTokenProvider()
    }


}


