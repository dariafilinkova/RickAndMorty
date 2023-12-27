package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.no_internet)
        )
        Spacer(modifier = modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.offline_state),
            contentDescription = "offline mode",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(8.dp))
        Button(onClick = onRetryClick) {
            Text(
                text = stringResource(id = R.string.try_again)
            )
        }
    }
}