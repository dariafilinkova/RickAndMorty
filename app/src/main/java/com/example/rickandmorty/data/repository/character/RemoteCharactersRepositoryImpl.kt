package com.example.rickandmorty.data.repository.character

import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.domain.model.character.CharacterDetailsDto
import com.example.rickandmorty.domain.model.character.CharactersDto
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.IRemoteCharactersRepository
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.toItemCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteCharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IRemoteCharactersRepository {
    override suspend fun getCharacters(
        page: Int,
        name: String?,
        status: String?,
        gender: String?
    ): CharactersDto {
        return apiService.getCharacters(page, name, status, gender)
    }

    override fun getCharacterDetails(characterId: Int): Flow<Resource<CharacterDetails>> {
        return flow {
            emit(Resource.Loading())
            val response = apiService.getCharacterDetails(characterId)
            val characterDetail = response.toItemCharacter()
            emit(Resource.Success(data = characterDetail))
        }.catch {
            emit(Resource.Error(message = "No internet connection"))
        }
    }

    override suspend fun getCharactersForDetail(id: String): List<CharacterDetailsDto> {
        return apiService.getCharactersForDetail(id)
    }

}