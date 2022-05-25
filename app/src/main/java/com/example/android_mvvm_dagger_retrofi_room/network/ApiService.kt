package com.example.android_mvvm_dagger_retrofi_room.network

import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.models.apimodel.GetCityFromApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("latest/by-city")
    suspend fun getLatestCity(
        @Query("city") cityName: String,
    ) : Response<GetCityFromApi>

}