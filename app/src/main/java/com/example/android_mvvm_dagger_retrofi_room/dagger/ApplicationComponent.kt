package com.example.android_mvvm_dagger_retrofi_room.dagger

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.android_mvvm_dagger_retrofi_room.DataShowingActivity
import com.example.android_mvvm_dagger_retrofi_room.MainActivity
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,ViewModelModule::class,DataBaseModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun injectDataShowingActivity(dataShowingActivity: DataShowingActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

}