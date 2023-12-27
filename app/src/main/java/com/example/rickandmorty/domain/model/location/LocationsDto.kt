package com.example.rickandmorty.domain.model.location

import com.example.rickandmorty.domain.model.InfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<LocationResultDto>
)