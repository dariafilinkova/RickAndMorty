package com.example.rickandmorty.data.repository.episode

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.LocalDatabase
import com.example.rickandmorty.data.paging_source.EpisodesRemoteMediator
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.repositories.EpisodesRepository
import com.example.rickandmorty.network.IRemoteEpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val repository: IRemoteEpisodesRepository,
    private val database: LocalDatabase
) : EpisodesRepository {

    private val episodeDao = database.episodeDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = EpisodesRemoteMediator(
                repository = repository,
                database = database,
                name = name,
                episode = episode
            ),
            pagingSourceFactory = { episodeDao.getAllEpisodes(name,episode) }
        ).flow
    }

    override suspend fun getEpisode(episodeId: Int): Episode {
        return repository.getEpisode(episodeId)
    }

}
