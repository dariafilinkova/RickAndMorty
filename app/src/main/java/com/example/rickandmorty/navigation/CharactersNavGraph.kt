package com.example.rickandmorty.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmorty.navigation.CharactersDestinations.CHARACTER_ITEM_ID_KEY
import com.example.rickandmorty.navigation.CharactersDestinations.CHARACTER_ITEM_ROUTE
import com.example.rickandmorty.presentation.character_details.CharacterDetailsScreen
import com.example.rickandmorty.presentation.characters.CharactersScreen

const val CHARACTERS_GRAPH = "characters"

object CharactersDestinations {
    const val CHARACTERS_ROUTE = "root"
    const val CHARACTER_ITEM_ID_KEY = "characterId"
    const val CHARACTER_ITEM_ROUTE = "character"
}

fun NavGraphBuilder.addCharactersGraph(
    onCharacterSelected: (Int, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    composable(BottomBarTab.CHARACTERS.route) {from->
        CharactersScreen(onItemClick = { id -> onCharacterSelected(id, from) })
    }
    composable(
        route = "$CHARACTER_ITEM_ROUTE/{${CHARACTER_ITEM_ID_KEY}}",
        arguments = listOf(navArgument(CHARACTER_ITEM_ID_KEY) { type = NavType.IntType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val characterId = arguments.getInt(CHARACTER_ITEM_ID_KEY)
        CharacterDetailsScreen(characterId, upPress)
    }

}