package com.example.android_mvvm_dagger_retrofi_room.models

data class Station(
    val aQI: Int,
    val aqiInfo: AqiInfo,
    val cO: Double,
    val city: String,
    val countryCode: String,
    val division: String,
    val lat: Double,
    val lng: Double,
    val nO2: Double,
    val oZONE: Double,
    val pM10: Double,
    val pM25: Double,
    val placeName: String,
    val postalCode: String,
    val sO2: Double,
    val state: String,
    val updatedAt: String
)