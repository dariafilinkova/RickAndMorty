package com.example.rickandmorty.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.rickandmorty.utils.rememberAppState


@Composable
fun AppNavigation() {
    val appState = rememberAppState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                BottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute!!,
                    navigateToRoute = appState::navigateToBottomBarRoute
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> Snackbar(snackbarData) }
            )
        },
        //scaffoldState = appState.scaffoldState
    ) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = CHARACTERS_GRAPH,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            navGraph(
                onCharacterSelected = appState::navigateToCharacterDetails,
                upPress = appState::upPress,
                onLocationSelected = appState::navigateToLocationDetails,
                onEpisodeSelected = appState::navigateToEpisodeDetails
            )
        }
    }
}