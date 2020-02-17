package com.healios.dreams.data.api


import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory constructor() : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?,
                     retrofit: Retrofit?): CallAdapter<*, *>? {

        return returnType?.let {
            return try {
                // get enclosing type
                val enclosingType = (it as ParameterizedType)

                if (enclosingType.rawType != ApiResponse::class.java)
                    null
                else {
                    val type = enclosingType.actualTypeArguments[0]
                    ApiResponseCallAdapter<Any>(type)
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = ApiResponseCallAdapterFactory()
    }

}