package com.example.rickandmorty.data.repository.location

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.data.paging_source.LocationsRemoteMediator
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.repositories.LocationsRepository
import com.example.rickandmorty.network.IRemoteLocationsRepository
import com.example.rickandmorty.utils.toItemLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteLocationsRepository,
    private val database: LocalDatabase
) : LocationsRepository {

    private val locationDao = database.locationDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ): Flow<PagingData<ItemLocation>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = LocationsRemoteMediator(
                repository = remoteRepository,
                database = database,
                name = name,
                type = type,
                dimension = dimension
            ),
            pagingSourceFactory = { locationDao.getAllLocations(name, type, dimension) }
        ).flow
    }

    override suspend fun getLocationForDetail(id: String): Result<List<ItemLocation>> {
        return withContext(Dispatchers.IO) {
            try {
                val action =
                    listOf(remoteRepository.getLocationForDetails(id).toItemLocation())
                Result.success(action)
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }
}
