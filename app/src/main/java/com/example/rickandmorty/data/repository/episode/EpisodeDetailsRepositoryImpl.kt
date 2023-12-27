package com.example.rickandmorty.data.repository.episode

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.repositories.EpisodeDetailsRepository
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeDetailsRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteEpisodesRepository
) : EpisodeDetailsRepository {
    override fun getEpisodeDetails(id: Int): Flow<Resource<Episode>> {
        return remoteRepository.getEpisodeDetail(episodeId = id)
    }
}
