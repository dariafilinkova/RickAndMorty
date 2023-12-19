package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.domain.model.ItemCharacter
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.network.IRemoteCharactersRepository
import com.example.rickandmorty.utils.getPageIntFromUrl
import com.example.rickandmorty.utils.toItemCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteCharactersRepository
) : CharactersRepository {
    override suspend fun getAllCharacters(name: String?): Flow<PagingData<ItemCharacter>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CharactersPagingSource(repository = remoteRepository, name = name)
        }.flow
    }
}

class CharactersPagingSource(
    private val name: String? = null,
    private val repository: IRemoteCharactersRepository
) : PagingSource<Int, ItemCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, ItemCharacter>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            state.closestPageToPosition(anchorposition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemCharacter> {
        val pageNumber = params.key ?: 1
        return try {
            val charactersResponse = repository.getCharacters(pageNumber, name)
            val characters = charactersResponse.results.map { characterDto ->
                characterDto.toItemCharacter()
            }
            var nextPage: Int? = null
            if (charactersResponse.info.next != null) {

                /** fetching next Page wworks with production code but fails on unit tests due to android depedency
                 * val uri = Uri.parse(charactersResponse.info.next)
                 * nextpage = uri.getQueryParameter("page")?.toInt()
                 */

                nextPage = getPageIntFromUrl(charactersResponse.info.next!!)
            }
            LoadResult.Page(
                data = characters,
                nextKey = nextPage,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}