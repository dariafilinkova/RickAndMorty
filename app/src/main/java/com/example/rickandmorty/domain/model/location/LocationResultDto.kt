package com.example.rickandmorty.domain.model.location

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResultDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("dimension")
    val dimension: String,
    @SerialName("created")
    val created: String,
    @SerialName("residents")
    val residents: List<String>,
    @SerialName("url")
    val url: String
)