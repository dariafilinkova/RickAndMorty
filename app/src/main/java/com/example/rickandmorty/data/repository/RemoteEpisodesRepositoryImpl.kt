package com.example.rickandmorty.data.repository

import com.example.rickandmorty.domain.model.EpisodeDto
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import javax.inject.Inject

class RemoteEpisodesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IRemoteEpisodesRepository{
    override suspend fun getEpisodesForCharacterDetails(id: String): List<EpisodeDto> {
        return apiService.getEpisode(id)
    }
}