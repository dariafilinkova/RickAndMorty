package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersSearchSection(
    padding: PaddingValues
) {
    val viewModel: CharactersViewModel = hiltViewModel()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    SearchBar(
        query = searchText,
        modifier = Modifier
            .padding(padding),
        onQueryChange = viewModel::onSearchTextChange,
        onSearch = {
            viewModel.getCharacters()
            //viewModel.getCharactersWithFilters(name = searchText)
            viewModel.makeIsSearchingFalse()
        },
        active = isSearching,
        onActiveChange = {
            viewModel.onToogleSearch()
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (isSearching) {
                IconButton(
                    onClick = {
                        if (searchText.isNotEmpty()) {
                            viewModel.onSearchTextChange("")
                        } else {
                            viewModel.makeIsSearchingFalse()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            }
        }
    ) {
    }
}