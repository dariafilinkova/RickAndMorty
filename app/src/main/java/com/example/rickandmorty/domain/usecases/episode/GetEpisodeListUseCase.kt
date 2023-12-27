package com.example.rickandmorty.domain.usecases.episode

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeListUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {
    operator fun invoke(
        name: String? = null,
        episode: String? = null,
    ): Flow<PagingData<Episode>> {
        return episodesRepository.getAllEpisodes(name, episode)
    }
}