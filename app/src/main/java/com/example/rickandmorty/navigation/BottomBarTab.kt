package com.example.rickandmorty.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickandmorty.navigation.CharactersDestinations.CHARACTERS_ROUTE
import com.example.rickandmorty.navigation.EpisodesDestinations.EPISODES_ROUTE
import com.example.rickandmorty.navigation.LocationsDestinations.LOCATIONS_ROUTE

enum class BottomBarTab(
    val icon: ImageVector,
    val route: String
) {
    CHARACTERS(
        Icons.Default.Face,
        "$CHARACTERS_GRAPH/$CHARACTERS_ROUTE"
    ),
    LOCATIONS(
        Icons.Default.LocationOn,
        "$LOCATIONS_GRAPH/$LOCATIONS_ROUTE"
    ),
    EPISODES(
        Icons.Default.List,
        "$EPISODES_GRAPH/$EPISODES_ROUTE"
    ),
}