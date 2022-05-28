package com.example.city_search.di

import android.content.Context
import com.example.city_search.CityActivity
import com.example.city_search.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, DataBaseModule::class])
interface ApplicationComponent {

    fun inject(searchActivity: SearchActivity)

    fun injectCityActivity(cityActivity: CityActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}