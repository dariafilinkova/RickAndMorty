package com.example.rickandmorty.domain.model

data class ItemCharacter(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val gender: String,
    val status: String,
    val species:String,
    val origin:String,
)