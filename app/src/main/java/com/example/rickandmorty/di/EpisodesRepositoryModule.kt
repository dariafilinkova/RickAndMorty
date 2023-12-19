package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.EpisodesRepositoryImpl
import com.example.rickandmorty.data.repository.RemoteEpisodesRepositoryImpl
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EpisodesRepositoryModule {
    @Binds
    abstract fun providesEpisodeList(impl: EpisodesRepositoryImpl): EpisodesRepository

    @Binds
    abstract fun bindEpisodes(impl: RemoteEpisodesRepositoryImpl): IRemoteEpisodesRepository
}