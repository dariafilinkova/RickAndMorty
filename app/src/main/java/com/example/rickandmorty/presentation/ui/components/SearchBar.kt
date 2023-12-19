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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.rickandmorty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
        padding : PaddingValues
//    onItemClick: (Long) -> Unit,
//    idItemClickedInSearch: MutableLongState
) {

//    val searchText by contactViewModel.searchText.collectAsState()
//    val isSearching by contactViewModel.isSearching.collectAsState()
//    val contactsList by contactViewModel.contactsList.collectAsState()

    SearchBar(
        query = "",
        modifier = Modifier
            .padding(padding),
        onQueryChange = {  },
        onSearch = {  },
        active = false,
        onActiveChange = {
            //contactViewModel.onToogleSearch()
                         },
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (false) {
                IconButton(
                    onClick = {
//                        if (searchText.isNotEmpty()) {
//                            contactViewModel.onSearchTextChange("")
//                        } else {
//                            contactViewModel.makeIsSearchingFalse()
//                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            }
        }
    ) {
//        LazyColumn {
//            items(contactsList) { contact ->
////                ContactItem(
////                    contact = contact,
////                    onClick = {
////                        idItemClickedInSearch.longValue = contact.id
////                        onItemClick(idItemClickedInSearch.longValue)
////                    },
////                    onDelete = contactViewModel::removeContact
////                )
//            }
//        }
    }
}