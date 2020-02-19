package com.healios.dreams.di

import com.healios.dreams.data.DefaultLoginManager
import com.healios.dreams.data.LoginManager
import com.healios.dreams.data.PreferencesTokenProvider
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.data.api.DreaMSClient
import com.healios.dreams.data.api.TokenInterceptor

object InjectorUtils {

    fun  getLoginManager(): LoginManager {
        return DefaultLoginManager(DreaMSClient(getTokenProvider(),
            false).dreamMSService)
    }

    fun getTokenProvider(): TokenProvider {
        return PreferencesTokenProvider()
    }


}


