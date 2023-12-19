package com.example.rickandmorty.domain.model

import kotlinx.serialization.SerialName

data class CharacterDetailsDto (
    @SerialName("id")
    val id: Int,
    @SerialName("created")
    val created: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: LocationDto,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: Origin,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)