package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRepository {
    fun getCharacterDetails(id: Int): Flow<Resource<CharacterDetails>>
}