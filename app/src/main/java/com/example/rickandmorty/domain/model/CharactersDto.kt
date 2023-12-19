package com.example.rickandmorty.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<ResultDto>
)