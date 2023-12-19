package com.example.rickandmorty.domain.model

data class Filter(
    val name: String,
)

val statusFilters = listOf(
    Filter(name = "Alive"),
    Filter(name = "Dead"),
    Filter(name = "unknown"),
)
val genderFilters = listOf(
    Filter(name = "Female"),
    Filter(name = "Male"),
    Filter(name = "Genderless"),
    Filter(name = "unknown"),
)