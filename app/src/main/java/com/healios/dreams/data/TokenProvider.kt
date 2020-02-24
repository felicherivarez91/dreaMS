package com.healios.dreams.data

import android.content.Context
import android.content.SharedPreferences
import com.healios.dreams.DreaMSApp

interface TokenProvider {

    var token: String?
    fun tokenExists(): Boolean

}

interface UserPreferences {

    var defaultCountry: String?
}

class PreferencesProvider: TokenProvider, UserPreferences {

    private val prefs: SharedPreferences =
        DreaMSApp.instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    override var token: String?
        get() = prefs.getString(TOKEN_PREFS, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(TOKEN_PREFS, value)
            editor.apply()
        }

    override fun tokenExists(): Boolean {
        return !token.isNullOrBlank()
    }

    override var defaultCountry: String?
        get() = prefs.getString(DEFAULTCOUNTRY_PREFS, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(DEFAULTCOUNTRY_PREFS, value)
            editor.apply()
        }

    companion object {
        private const val PREFERENCES_NAME = "com.healios.dreams.prefs"
        private const val TOKEN_PREFS = "token"
        private const val DEFAULTCOUNTRY_PREFS = "default_country"

    }
}