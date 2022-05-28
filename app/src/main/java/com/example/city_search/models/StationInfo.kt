package com.example.city_search.models

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