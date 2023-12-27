package com.example.rickandmorty.domain.usecases.episode

import com.example.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {
    suspend operator fun invoke(episodeId: Int) = episodesRepository.getEpisode(episodeId)
}
