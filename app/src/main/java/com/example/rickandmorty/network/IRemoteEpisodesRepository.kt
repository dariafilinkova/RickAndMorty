package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.EpisodeDto

interface IRemoteEpisodesRepository {
    suspend fun getEpisodesForCharacterDetails(
        id : String
    ): List<EpisodeDto>
}