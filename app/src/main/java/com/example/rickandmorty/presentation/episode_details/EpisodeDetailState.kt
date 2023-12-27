package com.example.rickandmorty.presentation.episode_details

import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.model.episode.Episode

data class EpisodeDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: Episode? = null,
    val errorMessage: String? = "",
    val characters: List<ItemCharacter> = emptyList()
)