package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.location.LocationDetailsRepositoryImpl
import com.example.rickandmorty.data.repository.location.LocationsRepositoryImpl
import com.example.rickandmorty.data.repository.location.RemoteLocationsRepositoryImpl
import com.example.rickandmorty.domain.repositories.LocationDetailsRepository
import com.example.rickandmorty.domain.repositories.LocationsRepository
import com.example.rickandmorty.network.IRemoteLocationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    abstract fun providesLocations(impl: LocationsRepositoryImpl): LocationsRepository

    @Binds
    abstract fun bindLocations(impl: RemoteLocationsRepositoryImpl): IRemoteLocationsRepository

    @Binds
    abstract fun providesLocationDetailsRepository(impl: LocationDetailsRepositoryImpl): LocationDetailsRepository
}