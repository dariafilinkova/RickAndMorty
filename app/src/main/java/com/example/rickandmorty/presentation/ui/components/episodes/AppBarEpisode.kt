package com.example.rickandmorty.presentation.ui.components.episodes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.presentation.episodes.EpisodeListState
import com.example.rickandmorty.presentation.episodes.EpisodesViewModel
import com.example.rickandmorty.presentation.ui.components.MediumTopAppBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarEpisode(
    appBarName: Int,
    episodes: LazyPagingItems<Episode>?,
    onItemClick: (Int) -> Unit,
    state: EpisodeListState,
    isSheetOpen: MutableState<Boolean>,
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val stateForRefresh = rememberPullToRefreshState()

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

            EpisodesSearchSection(
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
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    rows = GridCells.Fixed(3)
                ) {
                    if (!stateForRefresh.isRefreshing) {
                        episodes?.let {
                            items(it.itemCount) { index ->
                                episodes[index]?.let { episode ->
                                    ItemEpisode(
                                        item = episode,
                                        onItemClick = { onItemClick(episode.id) }
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

@Composable
fun ItemEpisode(item: Episode, onItemClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .width(210.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = CutCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { onItemClick(item.id) }
        ) {
            Column() {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.episode,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(6.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Blue
                )
                Text(
                    text = item.air_date,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(6.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Magenta

                )
            }

        }
    }
}