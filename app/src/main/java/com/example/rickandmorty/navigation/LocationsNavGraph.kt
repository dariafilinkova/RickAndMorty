package com.example.rickandmorty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.presentation.locations.LocationsScreen


const val LOCATIONS_GRAPH = "locations"

object LocationsDestinations {
    const val LOCATIONS_ROUTE = "root"
}

fun NavGraphBuilder.addLocationsGraph() {
    composable(BottomBarTab.LOCATIONS.route) {
        LocationsScreen()
    }
}