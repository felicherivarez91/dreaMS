package com.healios.dreams.data

import android.content.Context
import android.content.SharedPreferences
import com.healios.dreams.DreaMSApp

interface TokenProvider {

    var token: String?
    fun tokenExists() : Boolean

}

class PreferencesTokenProvider: TokenProvider {

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

    companion object {
        private const val PREFERENCES_NAME = "com.healios.dreams.prefs"
        private const val TOKEN_PREFS = "avatar"
    }
}