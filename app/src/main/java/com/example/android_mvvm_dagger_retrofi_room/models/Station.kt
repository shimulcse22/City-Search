package com.example.android_mvvm_dagger_retrofi_room.models


import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Station(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stationId")
    val stationId : Long,

    @ColumnInfo(name = "cityName")
    val cityName : String,

    @ColumnInfo(name = "AQI")
    val aQI: Int,

    @ColumnInfo(name = "aqiInfo")
    val aqiInfoId: Long,

    @ColumnInfo(name = "CO")
    val cO: Double,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "division")
    val division: String,

    @ColumnInfo(name = "_id")
    val id: String,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lng")
    val lng: Double,

    @ColumnInfo(name = "NO2")
    val nO2: Double,

    @ColumnInfo(name = "OZONE")
    val oZONE: Double,

    @ColumnInfo(name ="PM10")
    val pM10: Double,

    @ColumnInfo(name = "PM25")
    val pM25: Double,

    @ColumnInfo(name = "placeId")
    val placeId: String,

    @ColumnInfo(name = "placeName")
    val placeName: String,

    @ColumnInfo(name = "postalCode")
    val postalCode: String,

    @ColumnInfo(name = "SO2")
    val sO2: Double,

    @ColumnInfo(name = "state")
    val state: String,

    @ColumnInfo(name="updateAt")
    val updatedAt: String,

)