package com.example.rickandmorty.presentation.location_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.presentation.ui.components.ErrorScreen
import com.example.rickandmorty.presentation.ui.components.shimmerBrush

@Composable
fun LocationDetailsScreen(
    locationId: Int,
    upPress: () -> Unit,
    viewModel: LocationDetailsViewModel = hiltViewModel(),
    onCharacterSelected: (Int) -> Unit,
) {
    val detailState = viewModel.locationState.collectAsState().value

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
    if(detailState.isError){
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
                    painter = painterResource(id = R.drawable.wallpaper_location),
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
                            text = it.type,
                            icon = Icons.Default.Lock,
                            iconColor = Color.Blue
                        )
                        LocationDetailRow(
                            text = it.dimension,
                            icon = Icons.Default.Star,
                            iconColor = Color.Yellow
                        )
                    }
                }
            }
        }
        ResidentsForLocation(
            characters = detailState.characterList,
            onCharacterSelected = onCharacterSelected
        )

    }
}

@Composable
fun ResidentsForLocation(characters: List<ItemCharacter>, onCharacterSelected: (Int) -> Unit) {
    characters.let { list ->
        if (list.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.residents),
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

@Composable
fun ItemCharacterInLocation(it: ItemCharacter, onCharacterSelected: (Int) -> Unit) {
    val showShimmer = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .size(160.dp)
            .clickable {
                onCharacterSelected(it.id)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = it.imageUrl,
            contentDescription = "item image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(
                    shimmerBrush(
                        targetValue = 1300f,
                        showShimmer = showShimmer.value
                    )
                ),
            onSuccess = {
                showShimmer.value = false
            },
        )
        Text(
            text = it.name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LocationDetailRow(text: String, icon: ImageVector, iconColor: Color) {
    Column(verticalArrangement = Arrangement.Center) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = iconColor
            )
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}