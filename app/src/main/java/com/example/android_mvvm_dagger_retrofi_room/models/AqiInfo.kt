package com.example.android_mvvm_dagger_retrofi_room.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "aqiInfoTable")
@JsonClass(generateAdapter = true)
data class AqiInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "aqiInfoId")
    val aqiInfoId : Long,

    @ColumnInfo(name="aqiInfo")
    val aqiInfo: Long,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "concentration")
    val concentration: Double,

    @ColumnInfo(name = "pollutant")
    val pollutant: String
)