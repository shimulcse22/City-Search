package com.example.city_search.di

import android.content.Context
import com.example.city_search.DataShowingActivity
import com.example.city_search.MainActivity
import dagger.BindsInstance
import dagger.Component
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