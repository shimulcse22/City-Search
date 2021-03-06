package com.example.city_search.models.apimodel

import com.example.city_search.models.StationInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StationFromApi(
    @Json(name = "AQI")
    val aQI: Int,
    @Json(name = "aqiInfo")
    val aqiInfoFromApi: AqiInfoFromApi,
    @Json(name = "CO")
    val cO: Double,
    @Json(name = "city")
    val city: String,
    @Json(name = "countryCode")
    val countryCode: String,
    @Json(name = "division")
    val division: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double,
    @Json(name = "NO2")
    val nO2: Double,
    @Json(name = "OZONE")
    val oZONE: Double,
    @Json(name = "PM10")
    val pM10: Double,
    @Json(name = "PM25")
    val pM25: Double,
    @Json(name = "placeId")
    val placeId: String,
    @Json(name = "placeName")
    val placeName: String,
    @Json(name = "postalCode")
    val postalCode: String,
    @Json(name = "SO2")
    val sO2: Double,
    @Json(name = "state")
    val state: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)

fun StationFromApi.toStationInfo(): StationInfo {
    return StationInfo(
        city,
        lat,
        lng,
        nO2,
        oZONE,
        aqiInfoFromApi.concentration,
        aqiInfoFromApi.category,
        aqiInfoFromApi.pollutant
    )
}