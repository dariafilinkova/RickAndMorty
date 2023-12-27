package com.example.rickandmorty.presentation.ui.components.location

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.presentation.locations.ItemLocation
import com.example.rickandmorty.presentation.locations.LocationListState
import com.example.rickandmorty.presentation.locations.LocationsViewModel
import com.example.rickandmorty.presentation.ui.components.MediumTopAppBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarLocation(
    appBarName: Int,
    locations: LazyPagingItems<ItemLocation>?,
    onItemClick: (Int) -> Unit,
    state: LocationListState,
    isSheetOpen: MutableState<Boolean>,
    viewModel: LocationsViewModel = hiltViewModel()
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

            LocationsSearchSection(
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
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .padding(top = 16.dp),
                ) {
                    if (!stateForRefresh.isRefreshing) {
                        locations?.let {
                            items(it.itemCount) { index ->
                                locations[index]?.let { location ->
                                    ItemLocation(
                                        itemLocation = location,
                                        onItemClick = { onItemClick(location.id) }
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