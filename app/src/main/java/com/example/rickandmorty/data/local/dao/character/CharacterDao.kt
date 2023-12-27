package com.example.rickandmorty.data.local.dao.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.domain.model.character.ItemCharacter

@Dao
interface CharacterDao {
    @Query(
        "SELECT * FROM character_table WHERE " +
                "(:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:status IS NULL OR status LIKE '%' || :status || '%') " +
                "AND (:gender IS NULL OR gender LIKE '%' || :gender || '%') "
    )
    fun getAllCharacters(
        name: String?,
        status: String?,
        gender: String?
    ): PagingSource<Int, ItemCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<ItemCharacter>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()
}