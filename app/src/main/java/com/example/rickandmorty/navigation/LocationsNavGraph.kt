package com.example.rickandmorty.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmorty.presentation.location_details.LocationDetailsScreen
import com.example.rickandmorty.presentation.locations.LocationsScreen


const val LOCATIONS_GRAPH = "locations"

object LocationsDestinations {
    const val LOCATIONS_ROUTE = "root"
    const val LOCATION_ITEM_ID_KEY = "locationId"
    const val LOCATION_ITEM_ROUTE = "location"
}

fun NavGraphBuilder.addLocationsGraph(
    onLocationSelected: (Int, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onCharacterSelected: (Int, NavBackStackEntry) -> Unit,
    onEpisodeSelected: (Int, NavBackStackEntry) -> Unit,
) {
    composable(BottomBarTab.LOCATIONS.route) { from ->
        LocationsScreen(onItemClick = { id -> onLocationSelected(id, from) })
    }
    composable(
        route = "${LocationsDestinations.LOCATION_ITEM_ROUTE}/{${LocationsDestinations.LOCATION_ITEM_ID_KEY}}",
        arguments = listOf(navArgument(LocationsDestinations.LOCATION_ITEM_ID_KEY) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val locationId = arguments.getInt(LocationsDestinations.LOCATION_ITEM_ID_KEY)
        LocationDetailsScreen(
            locationId,
            upPress,
            onCharacterSelected = { id -> onCharacterSelected(id, backStackEntry) }
        )
    }
}