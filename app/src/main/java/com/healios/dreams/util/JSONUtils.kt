package com.healios.dreams.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.healios.dreams.DreaMSApp
import java.io.IOException
import java.lang.reflect.Type


class JSONUtils {

    companion object {
        inline fun <reified T> getJsonDataFromAsset(fileName: String): T? {
            val jsonString: String
            return try {
                jsonString = DreaMSApp.instance.applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }
                val listType: Type = object : TypeToken<T>() {}.type
                Gson().fromJson(jsonString, listType)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                null
            }
        }
    }

}