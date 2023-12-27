package com.example.rickandmorty.domain.model.character

import com.example.rickandmorty.domain.model.InfoDto
import com.example.rickandmorty.domain.model.ResultDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<ResultDto>
)