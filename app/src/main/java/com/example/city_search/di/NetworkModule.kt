package com.example.city_search.di

import com.example.city_search.data.network.ApiService
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
    fun provideRetroFitInstance(mClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(mClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
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
            requestBuilder.header(CONTENT_TYPE, APPLICATION_JSON)
            requestBuilder.header(
                X_API_KEY, API_KEY,
            )
            chain.proceed(requestBuilder.build())
        })
        return mBuilder.build()
    }
    companion object{
        const val X_API_KEY = "x-api-key"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        const val API_KEY = "3b306c28026fe67e1b541436924ce4ca1f9efacd413efa167a290d9446f5b866"
    }

}

