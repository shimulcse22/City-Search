package com.example.android_mvvm_dagger_retrofi_room.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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
) : Serializable