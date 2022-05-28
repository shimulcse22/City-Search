package com.example.city_search

import android.app.Application
import com.example.city_search.di.ApplicationComponent
import com.example.city_search.di.DaggerApplicationComponent

class ProjectApplication : Application() {

    lateinit var  applicationComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}