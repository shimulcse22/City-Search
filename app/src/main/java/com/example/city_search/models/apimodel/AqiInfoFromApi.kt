package com.example.city_search.models.apimodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AqiInfoFromApi(
    @Json(name = "category")
    val category: String,
    @Json(name = "concentration")
    val concentration: Double,
    @Json(name = "pollutant")
    val pollutant: String
)