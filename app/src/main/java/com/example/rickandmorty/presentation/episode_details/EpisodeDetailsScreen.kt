package com.example.rickandmorty.presentation.episode_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.presentation.location_details.ItemCharacterInLocation
import com.example.rickandmorty.presentation.location_details.LocationDetailRow
import com.example.rickandmorty.presentation.ui.components.ErrorScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun EpisodeDetailsScreen(
    episodeId: Int,
    upPress: () -> Unit,
    onLocationSelected: (Int) -> Unit,
    onCharacterSelected: (Int) -> Unit,
    viewModel: EpisodeDetailsViewModel = hiltViewModel(),
) {
    val detailState = viewModel.episodeState.collectAsState().value
    if (detailState.isLoading) {
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
    if (detailState.isError) {
        ErrorScreen(
            onRetryClick = { viewModel.onClickRetry() }
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                color = Color.LightGray,
                shape = CircleShape
            ) {
                IconButton(onClick = upPress, modifier = Modifier.padding(4.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            }
        }
        detailState.data?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rick_and_morty_for_episode),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop
                )
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LocationDetailRow(
                            text = it.name,
                            icon = Icons.Default.Info,
                            iconColor = Color.Magenta
                        )
                        LocationDetailRow(
                            text = it.episode,
                            icon = Icons.Default.Lock,
                            iconColor = Color.Blue
                        )
                        LocationDetailRow(
                            text = it.air_date,
                            icon = Icons.Default.Star,
                            iconColor = Color.Yellow
                        )
                    }
                }
            }
        }
        ResidentsForEpisode(
            characters = detailState.characters,
            onCharacterSelected = onCharacterSelected
        )
    }
}

@Composable
fun ResidentsForEpisode(characters: List<ItemCharacter>, onCharacterSelected: (Int) -> Unit) {
    characters.let { list ->
        if (list.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.characters),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            LazyHorizontalGrid(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                rows = GridCells.Fixed(2)
            ) {
                items(list.size) { index ->
                    ItemCharacterInLocation(list[index], onCharacterSelected)
                }
            }
        }
    }

}