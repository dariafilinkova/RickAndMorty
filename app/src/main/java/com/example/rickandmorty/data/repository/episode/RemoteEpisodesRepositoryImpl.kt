package com.example.rickandmorty.data.repository.episode

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeDto
import com.example.rickandmorty.domain.model.episode.EpisodesDto
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.toEpisode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteEpisodesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IRemoteEpisodesRepository {
    override suspend fun getEpisodesForCharacterDetails(id: String): List<EpisodeDto> {
        return apiService.getEpisode(id)
    }

    override suspend fun getOneEpisodeForCharacterDetails(id: String): EpisodeDto {
        return apiService.getOneEpisodeForCharacterDetail(id)
    }

    override suspend fun getAllEpisodes(page: Int, name: String?, episode: String?): EpisodesDto {
        return apiService.getEpisodes(page, name, episode)
    }

    override suspend fun getEpisode(id: Int): Episode {
        return apiService.getEpisode(id).toEpisode()
    }

    override fun getEpisodeDetail(episodeId: Int): Flow<Resource<Episode>> {
        return flow {
            emit(Resource.Loading())
            val response = apiService.getEpisode(episodeId)
            val episodeDetail = response.toEpisode()
            emit(Resource.Success(data = episodeDetail))
        }.catch {
            emit(Resource.Error(message = "No internet"))
        }
    }
}
