package com.example.rickandmorty.di

import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import com.example.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.GetCharactersUseCase
import com.example.rickandmorty.domain.usecases.GetEpisodesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun providesCharactersUseCase(charactersRepository: CharactersRepository) =
        GetCharactersUseCase(charactersRepository)

    @Provides
    @Singleton
    fun providesCharacterDetailsUseCase(characterDetailsRepository: CharacterDetailsRepository) =
        GetCharacterDetailsUseCase(characterDetailsRepository)


    @Provides
    @Singleton
    fun providesEpisodesUseCase(episodeRepository: EpisodesRepository) = GetEpisodesUseCase(episodeRepository)


}