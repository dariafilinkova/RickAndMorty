package com.example.rickandmorty.data.repository

import android.util.Log
import com.example.rickandmorty.domain.model.Episode
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import com.example.rickandmorty.utils.toEpisode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val repository: IRemoteEpisodesRepository
) : EpisodesRepository {
    override suspend fun getEpisodesForCharacterDetail(episodeId: String): Result<List<Episode>> {
        return withContext(Dispatchers.IO) {
            try {

                val action = repository.getEpisodesForCharacterDetails(episodeId).map { singleEpisodeDTO ->
                    singleEpisodeDTO.toEpisode()
                }
                Log.d("here17","$action")
                Result.success(action)
            } catch (throwable: Throwable) {
                Log.d("here25","ff")
                Result.failure(throwable)
            }
        }
    }
}