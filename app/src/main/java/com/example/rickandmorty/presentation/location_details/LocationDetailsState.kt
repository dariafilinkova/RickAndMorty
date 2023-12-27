package com.example.rickandmorty.presentation.location_details

import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.model.location.ItemLocation

data class LocationDetailsState(
    val isLoading: Boolean = false,
    val data: ItemLocation? = null,
    val isError: Boolean = false,
    val characterList: List<ItemCharacter> = emptyList()
)