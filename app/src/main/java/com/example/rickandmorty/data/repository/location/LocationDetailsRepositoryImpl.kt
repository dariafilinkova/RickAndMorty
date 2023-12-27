package com.example.rickandmorty.data.repository.location

import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.repositories.LocationDetailsRepository
import com.example.rickandmorty.network.IRemoteLocationsRepository
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationDetailsRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteLocationsRepository
) : LocationDetailsRepository {

    override fun getLocationDetails(id: Int): Flow<Resource<ItemLocation>> {
        return remoteRepository.getLocationDetails(locationId = id)
    }
}