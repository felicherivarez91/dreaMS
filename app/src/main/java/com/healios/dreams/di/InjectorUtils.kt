package com.healios.dreams.di

import com.healios.dreams.data.*
import com.healios.dreams.data.api.DreaMSClient
import com.healios.dreams.util.managers.PermissionsManager

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

    fun getPermissionManager(): PermissionsManager {
        return PermissionsManager()
    }

    fun getAccountInfoProvider(): AccountInfoProvider {
        return PreferencesAccountInfoProvider()
    }


}


