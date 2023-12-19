package com.example.rickandmorty.di

import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

//    @ExperimentalSerializationApi
//    private val converter = json.asConverterFactory("application/json".toMediaType())

    private fun okHttpClient1(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(logging)

        return okHttpClient
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun providesAPI(): ApiService {

        val httpClient = okHttpClient1()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
            .create(ApiService::class.java)
    }
}