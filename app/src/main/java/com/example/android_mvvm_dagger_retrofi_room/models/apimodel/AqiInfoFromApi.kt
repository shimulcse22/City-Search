package com.example.android_mvvm_dagger_retrofi_room.models.apimodel


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