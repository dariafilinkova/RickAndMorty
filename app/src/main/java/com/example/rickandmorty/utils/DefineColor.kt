package com.example.rickandmorty.utils

import androidx.compose.ui.graphics.Color

fun defineColor(status: String): Color {
    return when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> {
            Color.Gray
        }
    }
}