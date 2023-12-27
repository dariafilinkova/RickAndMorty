package com.example.rickandmorty.domain.model.character

import com.example.rickandmorty.domain.model.LocationDto

data class CharacterDetails(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val location: LocationDto,
    val origin: String,
    val species: String,
    val type: String,
    val image: String,
    val episode: List<String>,
)
