package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeDto
import com.example.rickandmorty.domain.model.episode.EpisodesDto
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IRemoteEpisodesRepository {
    suspend fun getEpisodesForCharacterDetails(
        id: String
    ): List<EpisodeDto>

    suspend fun getOneEpisodeForCharacterDetails(
        id: String
    ): EpisodeDto

    suspend fun getAllEpisodes(
        page: Int,
        name: String? = null,
        episode: String? = null
    ): EpisodesDto

    suspend fun getEpisode(
        id: Int
    ): Episode

    fun getEpisodeDetail(episodeId: Int): Flow<Resource<Episode>>
}