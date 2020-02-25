package com.healios.dreams.util


import android.content.Context
import android.media.audiofx.EnvironmentalReverb
import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.healios.dreams.DreaMSApp
import java.io.*
import java.lang.StringBuilder
import java.lang.reflect.Type

class JSONUtils {

    companion object {
        private val TAG: String = JSONUtils::class.java.simpleName

        inline fun <reified T> getJsonDataFromAsset(fileName: String): T? {
            val jsonString: String
            return try {
                jsonString =
                    DreaMSApp.instance.applicationContext.assets.open(fileName).bufferedReader()
                        .use { it.readText() }
                val listType: Type = object : TypeToken<T>() {}.type
                Gson().fromJson(jsonString, listType)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                null
            }
        }

        fun writeJSONtoFile(fileName: String, jsonObject: Any) {
            val jsonString: String
            val fileOutputStream: FileOutputStream

            try {
                jsonString = Gson().toJson(jsonObject)

                fileOutputStream = DreaMSApp.instance.applicationContext.openFileOutput(
                    fileName,
                    Context.MODE_PRIVATE
                )
                fileOutputStream.write(jsonString.toByteArray())
                Log.d(TAG, String.format("Saved to %s/%s",DreaMSApp.instance.applicationContext.filesDir, fileName))
                fileOutputStream.close()
            } catch (exception: Exception) {
                //TODO:
                Log.e(TAG, exception.localizedMessage ?: "[ERROR] Cannot parse JSON Object!")
            }
        }

        inline fun <reified T>readDataModelFromJSONFile(fileName: String, cls: Class<T>) : T? {
            val jsonString : String
            val fileInputStream: FileInputStream

            return try {
                fileInputStream = DreaMSApp.instance.openFileInput(fileName)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder = StringBuilder()
                var text: String

                for (line in bufferedReader.readLines()) {
                    stringBuilder.append(line).append("\n")
                }

                jsonString = stringBuilder.toString()

                fileInputStream.close()

                //val listType: Type = object : TypeToken<T>() {}.type
                //return Gson().fromJson(jsonString, listType)

                Gson().fromJson(jsonString, cls)

            } catch (fileNotFound: FileNotFoundException) {
                 null
            } catch (ex: IOException) {
                null
            }

        }

    }

}