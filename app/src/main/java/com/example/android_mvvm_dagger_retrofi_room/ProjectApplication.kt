package com.example.android_mvvm_dagger_retrofi_room

import android.app.Application
import com.example.android_mvvm_dagger_retrofi_room.dagger.ApplicationComponent
import com.example.android_mvvm_dagger_retrofi_room.dagger.DaggerApplicationComponent

class ProjectApplication : Application() {

    lateinit var  applicationComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}