package com.example.rickandmorty.presentation.character_details

import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.domain.model.episode.Episode

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val data: CharacterDetails? = null,
    val isError: Boolean = false,
    val episodes: List<Episode> = emptyList()
)