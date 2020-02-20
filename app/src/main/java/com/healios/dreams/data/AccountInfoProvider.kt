package com.healios.dreams.data

import android.content.Context
import android.content.SharedPreferences
import com.healios.dreams.DreaMSApp

interface AccountInfoProvider {

    var avatar: Int
    fun isDefaultAvatar() : Boolean

}

class PreferencesAccountInfoProvider: AccountInfoProvider {

    private val DEFAULT_AVATAR: Int = 0

    private val prefs: SharedPreferences =
        DreaMSApp.instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    override var avatar: Int
        get() = prefs.getInt(ACCOUNT_AVATAR_PREFS, DEFAULT_AVATAR)
        set(value) {
            val editor = prefs.edit()
            editor.putInt(ACCOUNT_AVATAR_PREFS, value)
            editor.apply()
        }

    override fun isDefaultAvatar(): Boolean {
        return avatar == DEFAULT_AVATAR
    }

    companion object {
        private const val PREFERENCES_NAME = "com.healios.dreams.prefs"
        private const val ACCOUNT_AVATAR_PREFS = "account_avatar"
    }
}