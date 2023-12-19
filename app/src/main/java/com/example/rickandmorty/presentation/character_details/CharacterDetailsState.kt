package com.example.rickandmorty.presentation.character_details

import com.example.rickandmorty.domain.model.CharacterDetails

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val data: CharacterDetails? = null,
    val errorMessage: String? = ""
)