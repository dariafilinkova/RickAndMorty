package com.example.rickandmorty.data.repository.location

import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.model.location.LocationResultDto
import com.example.rickandmorty.domain.model.location.LocationsDto
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.network.IRemoteLocationsRepository
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.toLocationDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteLocationsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IRemoteLocationsRepository {
    override suspend fun getLocations(
        page: Int,
        name: String?,
        type: String?,
        dimension: String?
    ): LocationsDto {
        return apiService.getLocations(page, name, type, dimension)
    }

    override fun getLocationDetails(locationId: Int): Flow<Resource<ItemLocation>> {
        return flow {
            emit(Resource.Loading())
            val response = apiService.getLocationDetails(locationId)
            val locationDetail = response.toLocationDetail()
            emit(Resource.Success(data = locationDetail))
        }.catch {
            emit(Resource.Error(message = "No internet connection"))
        }

    }

    override suspend fun getLocationForDetails(id: String): LocationResultDto {
        return apiService.getLocationForDetail(id)
    }
}