package com.example.rickandmorty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable import com.example.rickandmorty.presentation.characters.CharactersScreen
import com.example.rickandmorty.presentation.episodes.EpisodesScreen

const val EPISODES_GRAPH = "episodes"

object EpisodesDestinations {
    const val EPISODES_ROUTE = "root"
}

fun NavGraphBuilder.addEpisodesGraph() {
    composable(BottomBarTab.EPISODES.route) {
        EpisodesScreen()
    }
}