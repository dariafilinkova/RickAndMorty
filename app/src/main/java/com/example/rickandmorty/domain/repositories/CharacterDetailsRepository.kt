package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.model.CharacterDetails

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(id: Int): Result<CharacterDetails>
}