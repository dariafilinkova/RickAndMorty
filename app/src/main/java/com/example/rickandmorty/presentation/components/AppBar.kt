package com.example.rickandmorty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.ui.theme.Blue1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWthScaffold(appBarName: Int) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MediumTopAppBar(scrollBehavior, appBarName) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(50) {
                ListItem(
                    headlineContent = { Text(text = "Item $it") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = null
                        )
                    }
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(scrollBehavior: TopAppBarScrollBehavior, appBarName: Int) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(id = R.string.characters),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Blue1
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = "filter",
                    tint = Blue1
                )
            }
        }
    )
}

