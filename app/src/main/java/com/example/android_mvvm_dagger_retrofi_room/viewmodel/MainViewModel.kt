package com.example.android_mvvm_dagger_retrofi_room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_mvvm_dagger_retrofi_room.models.GetCity
import com.example.android_mvvm_dagger_retrofi_room.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CityRepository) :ViewModel() {
    val cityLiveData : LiveData<GetCity>
    get() = repository.city

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCity()
        }
    }
}