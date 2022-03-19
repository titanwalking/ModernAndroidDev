package com.alicankorkmaz.modernandroiddev.network

import com.alicankorkmaz.modernandroiddev.network.exceptions.NoContentException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response


sealed class ApiResponse<out T> {

    data class Success<T>(val response: Response<T>) : ApiResponse<T>() {
        val statusCode: StatusCode = getStatusCodeFromResponse(response)
        val data: T by lazy { response.body() ?: throw NoContentException(statusCode.code) }
        override fun toString(): String = "[ApiResponse.Success](data=$data)"
    }

    data class Error<T>(val response: Response<T>) : ApiResponse<T>() {
        val statusCode: StatusCode = getStatusCodeFromResponse(response)
        val errorBody: ResponseBody? = response.errorBody()
        override fun toString(): String =
            "[ApiResponse.Failure.Error-$statusCode](errorResponse=$response)"
    }

    data class Exception<T>(val exception: Throwable) : ApiResponse<T>() {
        val message: String? = exception.localizedMessage
        override fun toString(): String = "[ApiResponse.Failure.Exception](message=$message)"
    }

    companion object {
        fun <T> getStatusCodeFromResponse(response: Response<T>): StatusCode {
            return StatusCode.values().find { it.code == response.code() }
                ?: StatusCode.Unknown
        }
    }
}