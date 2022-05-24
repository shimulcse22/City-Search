package com.example.android_mvvm_dagger_retrofi_room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_mvvm_dagger_retrofi_room.models.AqiInfo
import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.models.Station

@Database(
    entities = [
        AqiInfo::class,
        GetCity::class,
        Station::class
               ],
    version = 1
)
abstract class CityDataBase : RoomDatabase(){

    abstract val citDao : CityDao
}