package com.example.city_search.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.city_search.models.AqiInfo
import com.example.city_search.models.GetCity
import com.example.city_search.models.Station

@Database(
    entities = [
        AqiInfo::class,
        GetCity::class,
        Station::class
               ],
    version = 4
)
abstract class CityDataBase : RoomDatabase(){

    abstract val citDao : CityDao
}