package com.example.android_mvvm_dagger_retrofi_room.models.apimodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCityFromApi(
    @Json(name = "message")
    val message: String,
    @Json(name = "stations")
    val stations: List<StationFromApi>
)