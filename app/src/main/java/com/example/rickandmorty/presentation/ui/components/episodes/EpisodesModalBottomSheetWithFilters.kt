package com.example.rickandmorty.presentation.ui.components.episodes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.model.episodesFilters
import com.example.rickandmorty.presentation.episodes.EpisodesViewModel
import com.example.rickandmorty.presentation.ui.theme.Blue1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesModalBottomSheetWithFilters(
    isSheetOpen: MutableState<Boolean>,
    sheetState: SheetState
) {
    val viewModel: EpisodesViewModel = hiltViewModel()

    val isExpanded = remember {
        mutableStateOf(false)
    }
    val filter by viewModel.filter.collectAsState()

    if (isSheetOpen.value) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen.value = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 64.dp, end = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                ExposedDropdownMenuBox(
                    expanded = isExpanded.value,
                    modifier = Modifier.fillMaxWidth(),
                    onExpandedChange = { isExpanded.value = !isExpanded.value }) {
                    TextField(
                        value = filter,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.select_filter)) },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded.value,
                        onDismissRequest = { isExpanded.value = false }) {
                        episodesFilters.forEach { it ->
                            DropdownMenuItem(
                                text = { Text(text = it.name) },
                                onClick = {
                                    viewModel.onFilterTextChange(it.name)
                                    isExpanded.value = false
                                })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.getEpisodesWithFilters()
                        isSheetOpen.value = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue1)
                ) {
                    Text(
                        text = stringResource(id = R.string.filter),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.onFilterTextChange("")
                        viewModel.onSearchTextChange("")
                        viewModel.getEpisodes()
                        isSheetOpen.value = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.clear),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}