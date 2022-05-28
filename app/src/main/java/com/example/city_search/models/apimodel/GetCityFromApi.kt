package com.example.city_search.models.apimodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCityFromApi(
    @Json(name = "message")
    val message: String,
    @Json(name = "stations")
    val stations: List<StationFromApi>
)