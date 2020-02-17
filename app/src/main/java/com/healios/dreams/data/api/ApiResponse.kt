package com.healios.dreams.data.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiResponse<R>(private val call: Call<R>) {

    //Sync calls
    fun run(responseHandler: (R?, Throwable?) -> Unit) {
        // run in the same thread
        try {
            // call and handle response
            val response = call.execute()
            handleResponse(response, responseHandler)

        } catch (t: IOException) {
            responseHandler(null, t)
        }
    }

    //Async calls
    fun process(responseHandler: (R?, Throwable?) -> Unit) {
        // define callback
        val callback = object : Callback<R> {

            override fun onFailure(call: Call<R>?, t: Throwable?) =
                responseHandler(null, t)

            override fun onResponse(call: Call<R>?, r: Response<R>?) =
                handleResponse(r, responseHandler)
        }

        call.enqueue(callback)
    }

    private fun handleResponse(response: Response<R>?, handler: (R?, Throwable?) -> Unit) {
        if (response?.isSuccessful == true) {
            handler(response.body(), null)
        } else {
            if (response?.code() in 400..511)

                if(response?.errorBody() != null) {
                    val jsonErroBody = JSONObject(response?.errorBody()?.string())
                    val message = jsonErroBody["msg"].toString()
                    handler(null, ApiException(response, message))
                }else {
                    handler(null, HttpException(response))
                }

            else handler(response?.body(), null)
        }
    }
}