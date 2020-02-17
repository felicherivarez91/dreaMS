package com.healios.dreams.di

import com.healios.dreams.data.DefaultLoginManager
import com.healios.dreams.data.LoginManager
import com.healios.dreams.data.PreferencesTokenProvider
import com.healios.dreams.data.TokenProvider
import com.healios.dreams.data.api.DreaMSClient

object InjectorUtils {

    fun  getLoginManager(): LoginManager {
        return DefaultLoginManager(DreaMSClient().dreamMSService)
    }

    fun getTokenProvider(): TokenProvider {
        return PreferencesTokenProvider()
    }


}


