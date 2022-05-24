package com.example.android_mvvm_dagger_retrofi_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_mvvm_dagger_retrofi_room.db.CityDataBase
import com.example.android_mvvm_dagger_retrofi_room.models.Station
import com.example.android_mvvm_dagger_retrofi_room.models.apimodel.GetCityFromApi

import com.example.android_mvvm_dagger_retrofi_room.network.ApiService
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val apiService: ApiService,
    private val cityDataBase: CityDataBase
){
    private val _city = MutableLiveData<GetCityFromApi>()

    val city : LiveData<GetCityFromApi>
    get() = _city

    lateinit var station : Station

    suspend fun getCity(){
        val result = apiService.getLatestCity()

        if(result.isSuccessful && result.body() !=null){
            _city.postValue(result.body())

            val cityName = "Bangalore"


        }
    }

//    private val _products = MutableLiveData<List<Product>>()
//    val products: LiveData<List<Product>>
//        get() = _products
//
//    suspend fun getProducts(){
//        val result = apiService.getProducts()
//        if(result.isSuccessful && result.body() != null){
//            //fakerDB.getFakerDAO().addProducts(result.body()!!)
//            _products.postValue(result.body())
//        }
//    }
}