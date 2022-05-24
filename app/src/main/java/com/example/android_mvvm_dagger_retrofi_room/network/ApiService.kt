package com.example.android_mvvm_dagger_retrofi_room.network

import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.models.apimodel.GetCityFromApi
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("latest/by-city?city=Bengaluru")
    suspend fun getLatestCity() : Response<GetCityFromApi>

}