package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.model.Episode

interface EpisodesRepository {
    suspend fun getEpisodesForCharacterDetail(episodeId: String): Result<List<Episode>>
}