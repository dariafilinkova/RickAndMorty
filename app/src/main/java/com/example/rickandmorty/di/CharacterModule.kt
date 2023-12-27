package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.character.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.data.repository.character.CharactersRepositoryImpl
import com.example.rickandmorty.data.repository.character.RemoteCharactersRepositoryImpl
import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.network.IRemoteCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterModule {

    @Binds
    abstract fun providesCharacterRepository(impl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    abstract fun bindCharacters(impl: RemoteCharactersRepositoryImpl): IRemoteCharactersRepository

    @Binds
    abstract fun providesCharacterDetailsRepository(impl: CharacterDetailsRepositoryImpl): CharacterDetailsRepository
}