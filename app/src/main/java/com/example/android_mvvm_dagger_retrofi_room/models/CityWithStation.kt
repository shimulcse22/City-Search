package com.example.android_mvvm_dagger_retrofi_room.models

import androidx.room.Embedded
import androidx.room.Relation

data class CityWithStation(
    @Embedded
    val city: GetCity,

    @Relation(
        parentColumn = "cityName",
        entityColumn = "cityName"
    )
    val stations: List<Station>

)