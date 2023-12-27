package com.example.rickandmorty.di

import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.domain.repositories.EpisodeDetailsRepository
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import com.example.rickandmorty.domain.repositories.LocationDetailsRepository
import com.example.rickandmorty.domain.repositories.LocationsRepository
import com.example.rickandmorty.domain.usecases.character.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.character.GetCharactersUseCase
import com.example.rickandmorty.domain.usecases.episode.GetEpisodeDetailsUseCase
import com.example.rickandmorty.domain.usecases.episode.GetEpisodesUseCase
import com.example.rickandmorty.domain.usecases.location.GetLocationDetailsUseCase
import com.example.rickandmorty.domain.usecases.location.GetLocationsUseCase
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
    fun providesLocationsUseCase(locationsRepository: LocationsRepository) =
        GetLocationsUseCase(locationsRepository)

    @Provides
    @Singleton
    fun providesCharacterDetailsUseCase(characterDetailsRepository: CharacterDetailsRepository) =
        GetCharacterDetailsUseCase(characterDetailsRepository)

    @Provides
    @Singleton
    fun providesLocationDetailsUseCase(locationDetailsRepository: LocationDetailsRepository) =
        GetLocationDetailsUseCase(locationDetailsRepository)


    @Provides
    @Singleton
    fun providesEpisodesUseCase(episodeRepository: EpisodesRepository) =
        GetEpisodesUseCase(episodeRepository)

    @Provides
    @Singleton
    fun providesEpisodeDetailsUseCase(episodeDetailsRepository: EpisodeDetailsRepository) =
        GetEpisodeDetailsUseCase(episodeDetailsRepository)



}