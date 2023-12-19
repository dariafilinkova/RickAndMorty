package com.example.rickandmorty.presentation.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.ItemCharacter
import com.example.rickandmorty.presentation.characters.CharacterListState
import com.example.rickandmorty.presentation.ui.theme.Blue1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWthScaffold(
    appBarName: Int,
    isSheetOpen: MutableState<Boolean>,
    characters: LazyPagingItems<ItemCharacter>?,
    onItemClick: (Int) -> Unit,
    state: CharacterListState
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MediumTopAppBar(scrollBehavior, appBarName, isSheetOpen) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            SearchSection(
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(top = 16.dp),
            ) {
                characters?.let {
                    items(it.itemCount) { index ->
                        characters[index]?.let { character ->
                            ItemCharacter(
                                itemCharacter = character,
                                onItemClick = { onItemClick(character.id) }
                            )
                        }
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

