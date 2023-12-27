package com.example.rickandmorty.domain.repositories


import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailsRepository {
    fun getEpisodeDetails(id: Int): Flow<Resource<Episode>>
}