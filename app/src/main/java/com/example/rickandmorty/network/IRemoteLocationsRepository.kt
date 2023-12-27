package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.model.location.LocationResultDto
import com.example.rickandmorty.domain.model.location.LocationsDto
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IRemoteLocationsRepository {
    suspend fun getLocations(
        page: Int,
        name: String? = null,
        type: String? = null,
        dimension: String? = null,
    ): LocationsDto

    fun getLocationDetails(
        locationId: Int
    ): Flow<Resource<ItemLocation>>

    suspend fun getLocationForDetails(
        id: String
    ): LocationResultDto
}