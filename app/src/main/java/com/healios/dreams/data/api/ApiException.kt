package com.healios.dreams.data.api

import retrofit2.Response
import java.lang.RuntimeException

class ApiException(@field:Transient private val response: Response<*>, message: String) :
    RuntimeException(message) {


    fun response(): Response<*>? {
        return response
    }
}
