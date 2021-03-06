package com.example.city_search.data.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.city_search.data.db.CityDataBase
import com.example.city_search.models.AqiInfo
import com.example.city_search.models.GetCity
import com.example.city_search.models.Station
import com.example.city_search.models.apimodel.GetCityFromApi
import com.example.city_search.data.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val apiService: ApiService,
    private val cityDataBase: CityDataBase
) {
    private val _city = MutableLiveData<Response<GetCityFromApi>>()
    val city: MutableLiveData<Response<GetCityFromApi>>
        get() = _city

    var cityInsert = MutableLiveData<GetCity>()


    suspend fun getCity(cityName: String) {

        try {
            val result = apiService.getLatestCity(cityName)
            if (result.isSuccessful && result.body() != null) {
                _city.postValue(Response.Success(result.body()))
                cityInsert.value = GetCity(cityName, result.body()!!.message)
                cityDataBase.citDao.insertCity(cityInsert.value!!)

                for (item in result.body()!!.stations) {
                    val station = Station(
                        cityName.lowercase(),
                        item.aQI,
                        item.cO,
                        item.city,
                        item.countryCode,
                        item.division,
                        item.id,
                        item.lat,
                        item.lng,
                        item.nO2,
                        item.oZONE,
                        item.pM10,
                        item.pM25,
                        item.placeId,
                        item.placeName,
                        item.postalCode,
                        item.sO2,
                        item.state,
                        item.updatedAt
                    )
                    val aqiInfo = AqiInfo(
                        cityName.lowercase(),
                        item.aqiInfoFromApi.category,
                        item.aqiInfoFromApi.concentration,
                        item.aqiInfoFromApi.pollutant
                    )
                    cityDataBase.citDao.insertStation(station)
                    cityDataBase.citDao.insertAqiInfo(aqiInfo)
                }
            } else {
                _city.postValue(Response.Error(result.errorBody()!!.string()))
            }
        } catch (e: Exception) {
            _city.postValue(Response.Error(e.message!!.toString()))
        }
    }

    private var _cityFromDatabase: MutableList<String> = arrayListOf()
    val cityData: MutableList<String>
        get() = _cityFromDatabase


    suspend fun getCityFromDataBase() {
        val result = cityDataBase.citDao.getCityFromDataBase()
        if (result.isNotEmpty()) {
            _cityFromDatabase.addAll(result)
        }
    }

    private var _stationInfo = MutableLiveData<MutableList<Station>>()
    val stationInfo: MutableLiveData<MutableList<Station>>
        get() = _stationInfo


    var checkData = MutableLiveData<Boolean>()

    suspend fun stationAqiInfo(cityName: String) {
        try {
            val result = cityDataBase.citDao.getStation(cityName)
            Log.d("CITY REPOSITORY result", result.toString())
            if (result.isNotEmpty()) {
                checkData.value = true
                stationInfo.postValue(result)
            }
        } catch (e: Exception) {
            Log.d("CITY REPOSITORY exception", e.message!!)
            e.stackTrace
        }
    }

    private var _aqiInfo = MutableLiveData<AqiInfo>()
    val aqiInfo: LiveData<AqiInfo>
        get() = _aqiInfo

    suspend fun aqiInfo(cityName: String) {
        try {
            val result = cityDataBase.citDao.getAqi(cityName)
            Log.d("CITY REPOSITORY QI", result.toString())
            if (result.city.isNotEmpty()) {
                _aqiInfo.value = result
                Log.d("CITY REPOSITORY aqiInfo", _aqiInfo.value.toString())
            }
        } catch (e: Exception) {
            Log.d("CITY REPOSITORY ex", e.stackTraceToString()!!)
            e.stackTrace
        }
    }

    init {
        checkData.value = false
    }

}