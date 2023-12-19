package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.CharacterDetailsDto
import com.example.rickandmorty.domain.model.CharactersDto

interface IRemoteCharactersRepository {
    suspend fun getCharacters(
        page: Int,
        name: String? = null,
        status: String? = null,
        gender: String? = null,
    ): CharactersDto

    suspend fun getCharacterDetails(
        characterId: Int
    ): CharacterDetailsDto


}