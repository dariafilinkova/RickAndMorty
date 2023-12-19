package com.example.rickandmorty.domain.model

data class CharacterDetails(
    val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val location: String,
    val origin: String,
    val species: String,
    val type: String,
    val image: String,
    val episode: List<Int>,
)