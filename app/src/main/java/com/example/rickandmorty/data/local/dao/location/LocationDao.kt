package com.example.rickandmorty.data.local.dao.location

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.domain.model.location.ItemLocation

@Dao
interface LocationDao {
    @Query(
        "SELECT * FROM location_table WHERE " +
                "(:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:type IS NULL OR type LIKE '%' || :type || '%') " +
                "AND (:dimension IS NULL OR dimension LIKE '%' || :dimension || '%') "
    )
    fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ): PagingSource<Int, ItemLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocations(locations: List<ItemLocation>)

    @Query("DELETE FROM location_table")
    fun deleteLocations()
}