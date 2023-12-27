package com.example.rickandmorty.utils

import com.example.rickandmorty.domain.model.ResultDto
import com.example.rickandmorty.domain.model.character.CharacterDetails
import com.example.rickandmorty.domain.model.character.CharacterDetailsDto
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeDto
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.model.location.LocationResultDto

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        air_date = air_date,
        characters = characters,
        episode = episode,
        id = id,
        name = name,
        url = url,
        created = created
    )
}

fun LocationResultDto.toItemLocation(): ItemLocation {
    return ItemLocation(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents,
        url = url,
        created = created
    )
}

fun CharacterDetailsDto.toItemCharacter(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status,
        episode = episode,
        location = location,
        origin = origin.name,
        species = species,
        type = type
    )
}

fun CharacterDetails.toItemCharacter(): ItemCharacter {
    return ItemCharacter(
        id = id,
        name = name,
        status = status,
        imageUrl = image,
        species = species,
        gender = gender,
        origin = origin
    )
}
fun List<EpisodeDto>.toEpisode(): List<Episode> {
    return map { episode ->
        Episode(
            id = episode.id,
            name = episode.name,
            air_date = episode.air_date,
            episode = episode.episode,
            characters = episode.characters,
            url = episode.url,
            created = episode.created
        )
    }
}

fun List<ResultDto>.toItemCharacter(): List<ItemCharacter> {
    return map {
        ItemCharacter(
            id = it.id,
            name = it.name,
            status = it.status,
            imageUrl = it.image,
            species = it.species,
            gender = it.gender,
            origin = it.origin.name,
        )
    }
}

fun List<LocationResultDto>.toLocation(): List<ItemLocation> {
    return map { locationDto ->
        ItemLocation(
            id = locationDto.id,
            name = locationDto.name,
            type = locationDto.type,
            created = locationDto.created,
            url = locationDto.url,
            residents = locationDto.residents,
            dimension = locationDto.dimension
        )
    }
}


fun LocationResultDto.toLocationDetail(): ItemLocation {
    return ItemLocation(
        id = id,
        name = name,
        type = type,
        residents = residents,
        created = created,
        url = url,
        dimension = dimension
    )
}