package com.example.rickandmorty.presentation.characters

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.ui.components.CharactersModalBottomSheetWithFilters
import com.example.rickandmorty.presentation.ui.components.TopAppBarWthScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    onItemClick: (Int) -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val isSheetOpen = remember {
        mutableStateOf(false)
    }
    val viewModel: CharactersViewModel = hiltViewModel()
    val state = viewModel.characterListState.value

    TopAppBarWthScaffold(
        appBarName=  R.string.characters,
        isSheetOpen,
        onItemClick,
        state,
        onLocationClick = onLocationClick
    )
    CharactersModalBottomSheetWithFilters(isSheetOpen, sheetState)
}