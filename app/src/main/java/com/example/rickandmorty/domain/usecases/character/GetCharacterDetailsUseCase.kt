package com.example.rickandmorty.domain.usecases.character

import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    operator fun invoke(id: Int) = characterDetailsRepository.getCharacterDetails(id)
}