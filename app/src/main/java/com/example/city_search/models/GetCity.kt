package com.example.city_search.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class GetCity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cityName")
    val cityName: String,

    @ColumnInfo(name = "message")
    val message: String?
)