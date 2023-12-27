package com.example.rickandmorty.domain.usecases.character

import com.example.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersForDetailUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    //suspend operator fun invoke(id: String) = charactersRepository.getCharactersForDetail(id)
}