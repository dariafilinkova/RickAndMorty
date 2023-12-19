package com.example.rickandmorty.utils

import android.util.Log
import com.example.rickandmorty.domain.model.CharacterDetails
import com.example.rickandmorty.domain.model.CharacterDetailsDto
import com.example.rickandmorty.domain.model.ItemCharacter
import com.example.rickandmorty.domain.model.ResultDto
import java.lang.Exception

fun ResultDto.toItemCharacter(): ItemCharacter {
    return ItemCharacter(
        id = id,
        name = name,
        imageUrl = image,
        gender = gender,
        status = status,
        origin = origin.name,
        species = species
    )
}

fun CharacterDetailsDto.toItemCharacter() : CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status,
        episode = episode.map { episode ->
            getEpisodeIntFromUrl(episode) ?: 0

        },
        location = location.name,
        origin = origin.name,
        species = species,
        type = type
    )
}

fun getEpisodeIntFromUrl(url: String): Int? {
    return try {
        url.substringAfterLast("/").toInt()
    } catch (e: Exception) {
        e.message?.let { Log.d("converting url", it) }
        null
    }
}
