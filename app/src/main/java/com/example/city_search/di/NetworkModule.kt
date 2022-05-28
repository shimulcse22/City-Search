package com.example.city_search.di

import com.example.city_search.network.ApiService
import com.example.city_search.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetroFitInstance(mClient : OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(mClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) :ApiService{
        return retrofit.create(ApiService::class.java)
    }



    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val mBuilder = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .callTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)

        mBuilder.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("x-api-key", "3b306c28026fe67e1b541436924ce4ca1f9efacd413efa167a290d9446f5b866")
            chain.proceed(requestBuilder.build())
        })
        return mBuilder.build()
    }


}

