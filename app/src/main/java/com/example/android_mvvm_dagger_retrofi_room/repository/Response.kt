package com.example.android_mvvm_dagger_retrofi_room.repository

sealed class Response<T>(val data: T? = null, val error : String? = null){
    class Loading<T>(data: T?) : Response<T>(data)
    class Success<T>(data: T?= null) : Response<T>(data = data)
    class Error<T>(error: String) : Response<T>(error = error)
}
