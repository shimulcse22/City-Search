package com.example.android_mvvm_dagger_retrofi_room.dagger

import com.example.android_mvvm_dagger_retrofi_room.network.ApiService
import com.example.android_mvvm_dagger_retrofi_room.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetroFitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) :ApiService{
        return retrofit.create(ApiService::class.java)
    }
}