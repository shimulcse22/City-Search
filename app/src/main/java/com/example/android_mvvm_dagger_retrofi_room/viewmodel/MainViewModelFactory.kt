package com.example.android_mvvm_dagger_retrofi_room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm_dagger_retrofi_room.repository.CityRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: CityRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}