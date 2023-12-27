package com.example.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.episode.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    suspend fun getEpisode(episodeId: Int): Episode

    fun getAllEpisodes(
        name: String? = null,
        episode: String? = null,
    ): Flow<PagingData<Episode>>
}