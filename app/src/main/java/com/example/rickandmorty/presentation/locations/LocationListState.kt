package com.example.rickandmorty.presentation.locations

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.location.ItemLocation
import kotlinx.coroutines.flow.Flow

data class LocationListState(
    var isLoading: Boolean = false,
    val dataList: Flow<PagingData<ItemLocation>>? = null,
    val errorMessage: String = ""
)