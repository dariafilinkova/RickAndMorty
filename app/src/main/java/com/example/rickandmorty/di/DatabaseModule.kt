package com.example.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(
        app: Application
    ): LocalDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}