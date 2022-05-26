package com.example.android_mvvm_dagger_retrofi_room.models

data class StationInfo(
    val cityName: String,
    val lat: Double,
    val lng: Double,
    val nO2: Double,
    val oZONE: Double,
    val concentration: Double,
    val category: String,
    val pollutant: String
)