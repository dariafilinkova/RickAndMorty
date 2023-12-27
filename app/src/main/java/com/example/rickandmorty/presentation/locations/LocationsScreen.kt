package com.example.rickandmorty.presentation.locations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.presentation.ui.components.location.LocationsModalBottomSheetWithFilters
import com.example.rickandmorty.presentation.ui.components.location.AppBarLocation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    onItemClick: (Int) -> Unit,
) {
    val viewModel: LocationsViewModel = hiltViewModel()
    val state = viewModel.locationListState.value
    val locations = state.dataList?.collectAsLazyPagingItems()
    val isSheetOpen = remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()

    AppBarLocation(
        appBarName = R.string.locations,
        locations = locations,
        onItemClick = onItemClick,
        state = state,
        isSheetOpen = isSheetOpen
    )

    LocationsModalBottomSheetWithFilters(isSheetOpen = isSheetOpen, sheetState = sheetState)
}

@Composable
fun ItemLocation(itemLocation: ItemLocation, onItemClick: (Int) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            shape = CutCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { onItemClick(itemLocation.id) }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                Text(
                    text = itemLocation.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.type),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                    Text(
                        text = itemLocation.type,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Blue
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.dimension),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                    Text(
                        text = itemLocation.dimension,
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
}
