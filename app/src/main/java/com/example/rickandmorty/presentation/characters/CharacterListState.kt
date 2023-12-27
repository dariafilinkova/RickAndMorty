package com.example.rickandmorty.presentation.characters

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.character.ItemCharacter
import kotlinx.coroutines.flow.Flow

data class CharacterListState(
    var isLoading: Boolean = false,
    val dataList: Flow<PagingData<ItemCharacter>>? = null,
    val errorMessage: String = ""
)
