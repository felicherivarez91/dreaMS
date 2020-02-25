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
    var userId: String?
}

class PreferencesProvider: TokenProvider, UserPreferences {

    private val prefs: SharedPreferences =
        DreaMSApp.instance.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    override var token: String?
        //FIXME: Remove hardcoded user_token
        get() = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImN0eSI6IkpXVCJ9.eyJpYXQiOjE1ODI2NDU2NzcsImV4cCI6Mzc1ODI2NDU2NzcsInJvbGVzIjpbIlJPTEVfUEFUSUVOVCJdLCJ1c2VybmFtZSI6ImEzNTdmYmU4LTUzZDYtMTFlYS1iNTM4LTAyNDJhYzE3MDAwYiIsImlwIjoiMTcyLjIzLjAuNCIsInBob25lIjoiKzM0IDY4OS02MTYtMDMxIiwiZXhwZXJ0X2lkIjpudWxsLCJwYXRpZW50X2lkIjoiYTM1ZTIzZjYtNTNkNi0xMWVhLThiYzAtMDI0MmFjMTcwMDBiIiwidXNlcl9pZCI6ImEzNTdmYmU4LTUzZDYtMTFlYS1iNTM4LTAyNDJhYzE3MDAwYiIsImVtYWlsIjpudWxsfQ.fznNceARseyxNsWvii-xeK5mijMcbxZkDO9nZ4nuE8jCLdvC043Gc85xQx_2BwfVmJUGQiSnsXHW5fP6iWqLo8Ih2bvt23-yLV4Z9xKNEvGTz7kci6GZ5hlBh0onDzkNorMZ3sYFTR8APDjY4SUneDDeHPdQrbvxiBRRkUnsmu6Oaapf-lQVSpE9ZutpG6VOYVFX9ZUHHHoRLti4lPcesgqWhCanWPhabeEYenbm0KtWInGuSFV0x-V6FB-GCyO_6RV_rym6SQLID1OEYE_yJafWtMGpr9HGqmZZheWacmp8eH9cJ8xCRyJc6Q8bbdOgQekNRYY4hlvFkqR3UQgSXg"//prefs.getString(TOKEN_PREFS, null)
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
    override var userId: String?
        get() = prefs.getString(USER_ID_PREFS_KEY, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(DEFAULTCOUNTRY_PREFS, value)
            editor.apply()
        }

    companion object {
        private const val PREFERENCES_NAME = "com.healios.dreams.prefs"
        private const val TOKEN_PREFS = "token"
        private const val DEFAULTCOUNTRY_PREFS = "default_country"
        private const val USER_ID_PREFS_KEY = "user_id"

    }
}