package com.example.rickandmorty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.presentation.characters.CharactersScreen

const val CHARACTERS_GRAPH = "characters"

object CharactersDestinations {
    const val CHARACTERS_ROUTE = "root"
}

fun NavGraphBuilder.addCharactersGraph() {
    composable(BottomBarTab.CHARACTERS.route) {
        CharactersScreen()
    }
}