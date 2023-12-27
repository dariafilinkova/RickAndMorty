package com.example.rickandmorty.presentation.episodes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.ui.components.episodes.AppBarEpisode
import com.example.rickandmorty.presentation.ui.components.episodes.EpisodesModalBottomSheetWithFilters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesScreen(
    onItemClick: (Int) -> Unit,
    onCharacterClick: (Int) -> Unit,
) {
    val viewModel: EpisodesViewModel = hiltViewModel()
    val state = viewModel.episodeListState.value
    val episodes = state.data?.collectAsLazyPagingItems()
    val isSheetOpen = remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()

    AppBarEpisode(
        appBarName = R.string.episodes,
        episodes = episodes,
        onItemClick = onItemClick,
        state = state,
        isSheetOpen = isSheetOpen
    )

    EpisodesModalBottomSheetWithFilters(isSheetOpen = isSheetOpen, sheetState = sheetState)
}