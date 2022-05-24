package com.example.android_mvvm_dagger_retrofi_room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm_dagger_retrofi_room.repository.CityRepository
import javax.inject.Inject
import javax.inject.Provider
@Suppress("UNCHECKED_CAST")
class AppViewModelFactory @Inject constructor(
    private val creators: Map<Class<*>, @JvmSuppressWildcards ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creators[modelClass] as T
    }
}
