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
    @Query("SELECT * FROM aqiInfoTable WHERE city = :cityName")
    suspend fun getAqiInfoAndStation(cityName : String) : List<AqiInfoAndStation>

    @Transaction
    @Query("SELECT cityName from GetCity")
    suspend fun getCityFromDataBase() : List<String>

    @Transaction
    @Query("SELECT * FROM station WHERE cityName = :cityName")
    suspend fun getStation(cityName: String) : MutableList<Station>

    @Transaction
    @Query("SELECT * FROM aqiInfoTable WHERE city = :cityName")
    suspend fun getAqi(cityName: String) : AqiInfo
}