package com.example.rickandmorty.domain.model.episode

import com.example.rickandmorty.domain.model.InfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesDto (
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<EpisodeDto>
)