package com.example.rickandmorty.domain.usecases.character

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharactersRepository
) {
    operator fun invoke(
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ): Flow<PagingData<ItemCharacter>> {
        return characterRepository.getAllCharacters(name, status, gender)

    }
}