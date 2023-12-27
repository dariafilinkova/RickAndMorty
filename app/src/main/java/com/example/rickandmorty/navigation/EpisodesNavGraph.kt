package com.example.rickandmorty.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmorty.presentation.episode_details.EpisodeDetailsScreen
import com.example.rickandmorty.presentation.episodes.EpisodesScreen

const val EPISODES_GRAPH = "episodes"

object EpisodesDestinations {
    const val EPISODES_ROUTE = "root"
    const val EPISODE_ITEM_ID_KEY = "episodeId"
    const val EPISODE_ITEM_ROUTE = "episode"
}

fun NavGraphBuilder.addEpisodesGraph(
    onLocationSelected: (Int, NavBackStackEntry) -> Unit,
    onCharacterSelected: (Int, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,

    onEpisodeSelected: (Int, NavBackStackEntry) -> Unit,
) {
    composable(BottomBarTab.EPISODES.route) { from ->
        EpisodesScreen(
            onItemClick = { id -> onLocationSelected(id, from) },
            onCharacterClick = { id -> onCharacterSelected(id, from) })
    }
    composable(
        route = "${EpisodesDestinations.EPISODE_ITEM_ROUTE}/{${EpisodesDestinations.EPISODE_ITEM_ID_KEY}}",
        arguments = listOf(navArgument(EpisodesDestinations.EPISODE_ITEM_ID_KEY) {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val episodeId = arguments.getInt(EpisodesDestinations.EPISODE_ITEM_ID_KEY)
        EpisodeDetailsScreen(
            episodeId,
            upPress,
            onLocationSelected = { id -> onLocationSelected(id, backStackEntry) },
            onCharacterSelected = { id -> onCharacterSelected(id, backStackEntry) })
    }
}