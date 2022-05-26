package com.example.android_mvvm_dagger_retrofi_room.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aqiInfoTable")

data class AqiInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "aqiInfoId")
    val aqiInfoId : Long,

    @ColumnInfo(name="city")
    val city: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "concentration")
    val concentration: Double,

    @ColumnInfo(name = "pollutant")
    val pollutant: String
)