package com.healios.dreams.di

import com.healios.dreams.data.*
import com.healios.dreams.data.api.DreaMSClient

object InjectorUtils {

    fun  getLoginManager(): LoginManager {
        return DefaultLoginManager(DreaMSClient(getTokenProvider(),
            false).dreamMSService)
    }

    fun getTokenProvider(): TokenProvider {
        return PreferencesProvider()
    }

    fun getCountryRepository(): CountryRepository {
        return JSONCountryRepository()
    }

    fun getUserPreferences(): UserPreferences {
        return PreferencesProvider()
    }

    fun getUserManager(): UserManager {
        return DefaultUserManager(DreaMSClient(getTokenProvider(),
            true).dreamMSService)
    }

    fun getUserCollectionDataRepository(): UserCollectionDataRepository {
        return JSONUserCollectionDataRepository()
    }


}


