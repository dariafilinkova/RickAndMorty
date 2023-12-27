package com.example.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.location.ItemLocation
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getAllLocations(
        name: String? = null,
        type: String? = null,
        dimension: String? = null
    ): Flow<PagingData<ItemLocation>>

    suspend fun getLocationForDetail(id: String): Result<List<ItemLocation>>

}