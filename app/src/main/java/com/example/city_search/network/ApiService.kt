package com.example.city_search.network

import com.example.city_search.models.apimodel.GetCityFromApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("latest/by-city")
    suspend fun getLatestCity(
        @Query("city") cityName: String,
    ) : Response<GetCityFromApi>

}