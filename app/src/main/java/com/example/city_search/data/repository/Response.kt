package com.example.city_search.data.repository

sealed class Response<T>(val data: T? = null, val error: String? = null) {
    class Loading<T>(data: T?) : Response<T>(data)
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(error: String) : Response<T>(error = error)
}
