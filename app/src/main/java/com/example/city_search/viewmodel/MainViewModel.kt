package com.example.city_search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city_search.models.AqiInfo
import com.example.city_search.models.Station
import com.example.city_search.models.apimodel.GetCityFromApi
import com.example.city_search.repository.CityRepository
import com.example.city_search.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: CityRepository) :ViewModel() {
    val cityLiveData : MutableLiveData<Response<GetCityFromApi>>
    get() = repository.city

    val searchData = MutableLiveData<String>()

    val placeName = MutableLiveData<String>()

    val cityLiveDataFromDataBase : List<String>
    get() = repository.cityData

    val stationData : MutableLiveData<MutableList<Station>>
    get() = repository.stationInfo

    val aqiData : LiveData<AqiInfo>
        get() = repository.aqiInfo

    val checkData : MutableLiveData<Boolean>
    get() = repository.checkData

    init {

        searchData.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            repository.getCityFromDataBase()
        }

    }

    suspend fun getCityFromAPi(){

        repository.getCity(searchData.value!!)
    }

    suspend fun getStationAndAqiInfo(){
        Log.d("SHIMUL3 sat",searchData.value.toString())
        repository.stationAqiInfo(searchData.value!!)
    }

    suspend fun getAqiInfo(){
        Log.d("SHIMUL3 aqi",searchData.value.toString())
        repository.aqiInfo(searchData.value!!)
    }
}