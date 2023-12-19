package com.example.rickandmorty.domain.model

data class Filter(
    val name: String,
)

val statusFilters = listOf(
    Filter(name = "alive"),
    Filter(name = "dead"),
    Filter(name = "unknown"),
)
val genderFilters = listOf(
    Filter(name = "female"),
    Filter(name = "male"),
    Filter(name = "genderless"),
    Filter(name = "unknown"),
)