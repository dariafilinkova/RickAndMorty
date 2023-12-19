package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {
    suspend operator fun invoke(id: String) = episodesRepository.getEpisodesForCharacterDetail(id)
}
