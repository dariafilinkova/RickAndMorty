package com.example.rickandmorty.data.local.dao.episode

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.domain.model.episode.Episode

@Dao
interface EpisodeDao {
    @Query(
        "SELECT * FROM episode_table WHERE " +
                "(:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:episode IS NULL OR episode LIKE '%' || :episode || '%') "
    )
    fun getAllEpisodes(
        name: String?,
        episode: String?,
    ): PagingSource<Int, Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllEpisodes(episodes: List<Episode>)

    @Query("DELETE FROM episode_table")
    suspend fun deleteAllEpisodes()
}