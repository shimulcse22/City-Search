package com.example.android_mvvm_dagger_retrofi_room.dagger

import androidx.lifecycle.ViewModel
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun mainViewModel(mainViewModel: MainViewModel) : ViewModel
}