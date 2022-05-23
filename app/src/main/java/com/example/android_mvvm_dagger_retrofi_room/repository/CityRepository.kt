package com.example.android_mvvm_dagger_retrofi_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.network.ApiService
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val apiService: ApiService
){
    private val _city = MutableLiveData<GetCity>()

    val city : LiveData<GetCity>
    get() = _city

    suspend fun getCity(){
        val result = apiService.getLatestCity()

        if(result.isSuccessful && result.body() !=null){
            _city.postValue(result.body())
        }
    }
}