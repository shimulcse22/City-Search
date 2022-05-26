package com.example.android_mvvm_dagger_retrofi_room.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_mvvm_dagger_retrofi_room.db.CityDataBase
import com.example.android_mvvm_dagger_retrofi_room.models.AqiInfo
import com.example.android_mvvm_dagger_retrofi_room.models.AqiInfoAndStation
import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.models.Station
import com.example.android_mvvm_dagger_retrofi_room.models.apimodel.GetCityFromApi
import com.example.android_mvvm_dagger_retrofi_room.network.ApiService
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val apiService: ApiService,
    private val cityDataBase: CityDataBase
){
    private val _city = MutableLiveData<Response<GetCityFromApi>>()
    val city : MutableLiveData<Response<GetCityFromApi>>
    get() = _city

    var cityInsert  = MutableLiveData<GetCity>()




    suspend fun getCity(cityName : String) {

        try {
            val result = apiService.getLatestCity(cityName)
            if(result.isSuccessful && result.body() !=null){
                _city.postValue(Response.Success(result.body()))
                cityInsert.value = GetCity(cityName,result.body()!!.message)
                cityDataBase.citDao.insertCity(cityInsert.value!!)

                for(item in result.body()!!.stations){
                    val station = Station(
                        cityName.lowercase(),item.aQI, item.cO,item.city,item.countryCode,item.division,item.id,item.lat,item.lng,item.nO2,
                        item.oZONE,item.pM10,item.pM25,item.placeId,item.placeName,item.postalCode,item.sO2,item.state,item.updatedAt
                    )
                    val aqiInfo = AqiInfo(
                        0,
                        cityName.lowercase(),
                        item.aqiInfoFromApi.category,
                        item.aqiInfoFromApi.concentration,
                        item.aqiInfoFromApi.pollutant
                    )
                    cityDataBase.citDao.insertStation(station)
                    cityDataBase.citDao.insertAqiInfo(aqiInfo)
                }
            } else{
                _city.postValue(Response.Error(result.errorBody()!!.string()))
            }
        }catch (e: Exception){
            _city.postValue(Response.Error(e.message!!.toString()))
        }
    }

    private var _cityFromDatabase : MutableList<String> = arrayListOf()
    val cityData : MutableList<String>
        get() = _cityFromDatabase


    suspend fun getCityFromDataBase(){
        val result = cityDataBase.citDao.getCityFromDataBase()
        if(result.isNotEmpty()){
            _cityFromDatabase.addAll(result)
        }
    }

    private var _stationInfo : MutableList<Station> = arrayListOf()
    val stationInfo : MutableList<Station>
    get() = _stationInfo



    var checkData = MutableLiveData<Boolean>()

    suspend fun stationAqiInfo(cityName: String){
        try {
            val result = cityDataBase.citDao.getStation(cityName)
            Log.d("SHIMUL2", result.toString())
            if(result.isNotEmpty()){
                checkData.value = true
                stationInfo.addAll(result)
                Log.d("SHIMUL2", result.toString())
            }
        }catch (e :Exception){
            Log.d("SHIMUL2 ex",e.message!!)
            e.stackTrace
        }
    }

    private var _aqiInfo = MutableLiveData<AqiInfo>()
    val aqiInfo : LiveData<AqiInfo>
        get() = _aqiInfo

    suspend fun aqiInfo(cityName: String){
        try {
            val result = cityDataBase.citDao.getAqi(cityName)
            Log.d("SHIMUL QI", result.toString())
            if(result.city.isNotEmpty()){
                _aqiInfo.value= result
                Log.d("SHIMUL QI dsg", _aqiInfo.value.toString())
            }
        }catch (e :Exception){
            Log.d("SHIMUL QI ex",e.stackTraceToString()!!)
            e.stackTrace
        }
    }

    init {
        checkData.value = false
    }

}