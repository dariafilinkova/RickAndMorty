package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Circle(color: Color) {
    Canvas(
        modifier = Modifier
            .size(size = 16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = color,
            radius = size.minDimension / 4,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
        )
    }
}

