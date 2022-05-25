package com.example.android_mvvm_dagger_retrofi_room.models

import androidx.room.Embedded
import androidx.room.Relation

data class AqiInfoAndStation(
    @Embedded
    val aqiInfo : AqiInfo,

    @Relation(
        parentColumn = "city",
        entityColumn = "city"
    )
    val station: Station
)
