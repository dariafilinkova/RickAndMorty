package com.example.rickandmorty.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation

fun NavGraphBuilder.navGraph(
    onCharacterSelected: (Int, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = CHARACTERS_GRAPH,
        startDestination = BottomBarTab.CHARACTERS.route
    ) {
        addCharactersGraph(onCharacterSelected,upPress)
        addEpisodesGraph()
        addLocationsGraph()
    }
}