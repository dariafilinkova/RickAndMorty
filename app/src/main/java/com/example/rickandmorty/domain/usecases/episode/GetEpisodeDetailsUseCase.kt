package com.example.rickandmorty.domain.usecases.episode

import com.example.rickandmorty.domain.repositories.EpisodeDetailsRepository
import javax.inject.Inject

class GetEpisodeDetailsUseCase @Inject constructor(
    private val episodeDetailsRepository: EpisodeDetailsRepository
) {
    operator fun invoke(id: Int) = episodeDetailsRepository.getEpisodeDetails(id)
}