package com.example.rickandmorty.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.model.remote_key.CharacterRemoteKeys
import com.example.rickandmorty.network.IRemoteCharactersRepository
import com.example.rickandmorty.utils.toItemCharacter
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val repository: IRemoteCharactersRepository,
    private val database: LocalDatabase,
    private val name: String?,
    private val status: String?,
    private val gender: String?
) : RemoteMediator<Int, ItemCharacter>() {

    private val characterDao = database.characterDao()
    private val characterRemoteKeysDao = database.characterRemoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemCharacter>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response =
                repository.getCharacters(page = page, name = name, status = status, gender = gender)

            if (response.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        characterRemoteKeysDao.deleteAllRemoteKeys()
                    }

                    val keys = response.results.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            prevPage = response.info.prev?.split("=")?.get(1)?.toInt(),
                            nextPage = response.info.next?.split("=")?.get(1)?.toInt(),
                        )
                    }

                    characterRemoteKeysDao.addAllRemoteKeys(characterRemoteKeys = keys)
                    characterDao.addCharacters(response.results.toItemCharacter())

                }
            }

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ItemCharacter>): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ItemCharacter>): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ItemCharacter>): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterRemoteKeysDao.getRemoteKeys(characterId = id)
            }
        }
    }

}

