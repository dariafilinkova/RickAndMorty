package com.example.rickandmorty.presentation.character_details

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.presentation.ui.components.CharacterDetailImage
import com.example.rickandmorty.presentation.ui.components.DropDownSection
import com.example.rickandmorty.presentation.ui.components.ErrorScreen
import com.example.rickandmorty.utils.defineColor
import kotlin.math.min


@Composable
fun CharacterDetailsScreen(
    characterId: Int,
    upPress: () -> Unit,
    onLocationSelected: (locationId: Int) -> Unit,
    onEpisodeSelected: (Int) -> Unit,
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
) {
    val detailsState = viewModel.detailsState.value
    Log.d("detailState", "$detailsState")
    val state = viewModel.characterDetailState.collectAsState().value

    val systemInDarkMode = isSystemInDarkTheme()
    val lazyListState = rememberLazyListState()
    val scrollOffset = min(
        1f.coerceAtMost(1f),
        (1 - (remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }.value / 2000f + remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value)).coerceAtLeast(
            0f
        )
    )

    val imageSize by animateFloatAsState(
        targetValue = 0.4f * scrollOffset,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = FastOutSlowInEasing
        ), label = "characterImage"

    )
    var colorPalette by remember {
        mutableStateOf<Palette?>(null)
    }
    val vibrantColor = when (systemInDarkMode) {

        true -> colorPalette?.darkVibrantSwatch?.rgb
        false -> colorPalette?.lightVibrantSwatch?.rgb
    } ?: 0
    val mutedColor = when (systemInDarkMode) {

        true -> colorPalette?.darkMutedSwatch?.rgb
        false -> colorPalette?.lightMutedSwatch?.rgb
    } ?: 0

    val gradient = Brush.verticalGradient(
        listOf(
            Color(vibrantColor),
            Color(mutedColor)
        )

    )
    val isClickedShowEpisodes = remember {
        mutableStateOf(false)
    }
    val isClickedShowLocation = remember {
        mutableStateOf(false)
    }

    if (state.isError) {
        ErrorScreen (onRetryClick = {
            viewModel.onClickRetry()
        })
    }
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.data?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(imageSize)
                        .background(
                            brush = gradient,
                            shape = RoundedCornerShape(
                                bottomStart = 32.dp, bottomEnd = 32.dp
                            ),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CharacterDetailImage(
                        it.image,
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .clip(RoundedCornerShape(8.dp))
                            .graphicsLayer {
                                alpha = min(1f, 1 - (scrollOffset / 600f))
                                translationY = -scrollOffset * 0.1f
                            }
                    ) { palette ->
                        colorPalette = palette
                    }
                }
                Text(
                    text = it.name,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = defineColor(it.status)

                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Text(text = it.status, style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            text = it.species,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = it.gender,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                DropDownSection(
                    isClickedSection = isClickedShowEpisodes,
                    name = stringResource(id = R.string.episodes),
                    function = {
                        EpisodesForCharacterDetail(
                            episodesList = state.episodes,
                            onEpisodeSelected
                        )
                    }
                )
                DropDownSection(
                    isClickedSection = isClickedShowLocation,
                    name = stringResource(id = R.string.location),
                    function = {
                        LocationCharacterDetail(
                            location = state.data.location.name,
                            locationId = it.location.url.split("/")[5].toInt(),
                            onLocationSelected = onLocationSelected
                        )
                    }
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp),
            shape = CircleShape
        ) {
            IconButton(onClick = upPress, modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        }
    }
}

@Composable
fun EpisodesForCharacterDetail(
    episodesList: List<Episode>,
    onEpisodeSelected: (Int) -> Unit
) {
    Log.d("episodesList", "$episodesList")
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = CutCornerShape(12.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(start = 8.dp, end = 8.dp)

    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            episodesList.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clickable {
                            onEpisodeSelected(it.id)
                        }
                ) {

                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Column {
                        Text(text = it.episode)
                        Text(text = it.name, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun LocationCharacterDetail(
    location: String,
    locationId: Int,
    onLocationSelected: (locationId: Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = CutCornerShape(12.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                onLocationSelected(locationId)
            }
    ) {
        Text(
            text = location,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}