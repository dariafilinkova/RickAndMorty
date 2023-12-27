package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.domain.model.character.CharacterDetailsDto
import com.example.rickandmorty.domain.model.character.CharactersDto
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IRemoteCharactersRepository {
    suspend fun getCharacters(
        page: Int,
        name: String? = null,
        status: String? = null,
        gender: String? = null,
    ): CharactersDto

    fun getCharacterDetails(
        characterId: Int
    ): Flow<Resource<CharacterDetails>>

    suspend fun getCharactersForDetail(
        id: String
    ): List<CharacterDetailsDto>


}