package com.example.rickandmorty.domain.model.character

import com.example.rickandmorty.domain.model.LocationDto
import com.example.rickandmorty.domain.model.Origin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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