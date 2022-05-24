package com.example.android_mvvm_dagger_retrofi_room.db

import androidx.room.*
import com.example.android_mvvm_dagger_retrofi_room.models.*

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Station)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAqiInfo(aqiInfo: AqiInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: GetCity)

    @Transaction
    @Query("SELECT * FROM aqiInfoTable WHERE aqiInfo = :aqiInfo")
    suspend fun getAqiInfoAndStation(aqiInfo : Long) : AqiInfoAndStation

    @Transaction
    @Query("SELECT * FROM station WHERE cityName = :cityName")
    suspend fun getCityWithStation(cityName : String) : List<CityWithStation>
}