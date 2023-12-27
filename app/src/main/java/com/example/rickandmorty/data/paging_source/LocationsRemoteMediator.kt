package com.example.rickandmorty.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.model.remote_key.LocationRemoteKeys
import com.example.rickandmorty.network.IRemoteLocationsRepository
import com.example.rickandmorty.utils.toLocation
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationsRemoteMediator @Inject constructor(
    private val repository: IRemoteLocationsRepository,
    private val database: LocalDatabase,
    private val name: String?,
    private val type: String?,
    private val dimension: String?,
) : RemoteMediator<Int, ItemLocation>() {

    private val locationDao = database.locationDao()
    private val locationRemoteKeysDao = database.locationRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemLocation>
    ): MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    val page = remoteKeys?.nextPage?.minus(1) ?: 1
                    page
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
            }

            val response = repository.getLocations(page = page)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        locationDao.deleteLocations()
                        locationRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val keys = response.results.map { location ->
                        LocationRemoteKeys(
                            id = location.id,
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt()
                        )
                    }

                    locationDao.addLocations(response.results.toLocation())
                    locationRemoteKeysDao.addRemoteKeys(remoteKeys = keys)
                }

            }

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ItemLocation>): LocationRemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(anchorPosition = it)?.id?.let { locationId ->
                locationRemoteKeysDao.getRemoteKeys(locationId = locationId)
            }
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, ItemLocation>): LocationRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { location ->
                locationRemoteKeysDao.getRemoteKeys(locationId = location.id)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, ItemLocation>): LocationRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { location ->
                locationRemoteKeysDao.getRemoteKeys(locationId = location.id)
            }
    }
}

