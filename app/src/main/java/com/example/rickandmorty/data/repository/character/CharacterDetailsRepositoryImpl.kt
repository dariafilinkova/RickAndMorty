package com.example.rickandmorty.data.repository.character

import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.network.IRemoteCharactersRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteCharactersRepository
) : CharacterDetailsRepository {

    override fun getCharacterDetails(id: Int): Flow<Resource<CharacterDetails>> {
           return remoteRepository.getCharacterDetails(characterId = id)
    }
}
