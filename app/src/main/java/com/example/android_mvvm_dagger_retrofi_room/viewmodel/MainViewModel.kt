package com.example.android_mvvm_dagger_retrofi_room.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.models.apimodel.GetCityFromApi
import com.example.android_mvvm_dagger_retrofi_room.repository.CityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: CityRepository) :ViewModel() {
    val cityLiveData : LiveData<GetCityFromApi>
    get() = repository.city

    init {
        viewModelScope.launch{
            Log.d("llllllll",cityLiveData.value.toString())
            repository.getCity()
        }
    }

//    val productsLiveData : LiveData<List<Product>>
//        get() = repository.products
//
//    init {
//        viewModelScope.launch {
//            repository.getProducts()
//        }
//    }

}