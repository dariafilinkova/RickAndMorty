package com.example.rickandmorty.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)