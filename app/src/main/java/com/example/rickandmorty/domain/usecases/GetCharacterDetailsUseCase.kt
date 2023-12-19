package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    suspend operator fun invoke(id: Int) = characterDetailsRepository.getCharacterDetails(id)
}