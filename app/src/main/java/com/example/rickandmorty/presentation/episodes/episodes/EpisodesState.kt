package com.example.rickandmorty.presentation.episodes.episodes

import com.example.rickandmorty.domain.model.Episode

data class EpisodesState(
    val isLoading: Boolean = false,
    val data: List<Episode> = emptyList(),
    val errorMessage: String? = ""
)