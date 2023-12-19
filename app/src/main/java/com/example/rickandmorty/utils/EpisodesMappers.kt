package com.example.rickandmorty.utils

import com.example.rickandmorty.domain.model.Episode
import com.example.rickandmorty.domain.model.EpisodeDto

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters.map { character ->
            getPageIntFromUrl(character) ?: 1
        },
        episode = episode,
        id = id,
        name = name
    )
}