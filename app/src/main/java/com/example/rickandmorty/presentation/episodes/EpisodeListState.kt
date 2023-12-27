package com.example.rickandmorty.presentation.episodes

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.episode.Episode
import kotlinx.coroutines.flow.Flow

data class EpisodeListState (
    var isLoading: Boolean = false,
    val data: Flow<PagingData<Episode>>? = null,
    val errorMessage: String? = ""
)
