package com.example.rickandmorty.data.repository

import com.example.rickandmorty.domain.model.CharacterDetailsDto
import com.example.rickandmorty.domain.model.CharactersDto
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.IRemoteCharactersRepository
import javax.inject.Inject

class RemoteCharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IRemoteCharactersRepository {
    override suspend fun getCharacters(page: Int, name: String?): CharactersDto {
        return apiService.getCharacters(page, name)
    }

    override suspend fun getCharacterDetails(characterId: Int): CharacterDetailsDto {
        return apiService.getCharacterDetails(characterId)
    }
}