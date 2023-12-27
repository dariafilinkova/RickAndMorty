package com.example.rickandmorty.data.repository.character

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.data.paging_source.CharactersRemoteMediator
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.network.IRemoteCharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteCharactersRepository,
    private val database: LocalDatabase
) : CharactersRepository {

    private val charactersDao = database.characterDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacters(
        name: String?,
        status: String?,
        gender: String?
    ): Flow<PagingData<ItemCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharactersRemoteMediator(
                repository = remoteRepository,
                database = database,
                name = name,
                status = status,
                gender = gender
            ),
            pagingSourceFactory = { charactersDao.getAllCharacters(name,status,gender) }
        ).flow
    }
}