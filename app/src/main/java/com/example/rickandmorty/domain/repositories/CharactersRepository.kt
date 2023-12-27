package com.example.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.character.ItemCharacter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ): Flow<PagingData<ItemCharacter>>

}