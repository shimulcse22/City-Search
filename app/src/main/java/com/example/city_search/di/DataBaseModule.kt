package com.example.city_search.di

import android.content.Context
import androidx.room.Room
import com.example.city_search.db.CityDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): CityDataBase {
        return Room.databaseBuilder(context, CityDataBase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object{
        const val DATABASE_NAME = "city"
    }
}