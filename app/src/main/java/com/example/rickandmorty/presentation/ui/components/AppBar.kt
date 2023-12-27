package com.example.rickandmorty.presentation.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharacterListState
import com.example.rickandmorty.presentation.characters.CharactersViewModel
import com.example.rickandmorty.presentation.ui.theme.Blue1
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWthScaffold(
    appBarName: Int,
    isSheetOpen: MutableState<Boolean>,
    onItemClick: (Int) -> Unit,
    state: CharacterListState,
    viewModel: CharactersViewModel = hiltViewModel(),
    onLocationClick: (Int) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val stateForRefresh = rememberPullToRefreshState()

    val characters = viewModel.getAllCharacters.collectAsLazyPagingItems()

    if (stateForRefresh.isRefreshing) {
        LaunchedEffect(true) {
            delay(1500)
            viewModel.refresh()
            stateForRefresh.endRefresh()
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MediumTopAppBar(scrollBehavior, appBarName, isSheetOpen) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {

            CharactersSearchSection(
                padding = padding
            )
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Card(shape = CircleShape) {

                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
            Box(Modifier.nestedScroll(stateForRefresh.nestedScrollConnection)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(top = 16.dp),
                ) {
                    if (!stateForRefresh.isRefreshing) {
                        characters?.let {
                            items(it.itemCount) { index ->
                                characters[index]?.let { character ->
                                    ItemCharacter(
                                        itemCharacter = character,
                                        onItemClick = { onItemClick(character.id) },
                                        onLocationClick = { onLocationClick(character.id) }
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (stateForRefresh.isRefreshing) {
                        CircularProgressIndicator()
                    } else {
                        CircularProgressIndicator(progress = { stateForRefresh.progress })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    appBarName: Int,
    isSheetOpen: MutableState<Boolean>
) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(id = appBarName),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Blue1
            )
        },
        actions = {
            IconButton(onClick = { isSheetOpen.value = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = "filter",
                    tint = Blue1
                )
            }
        }
    )
}

