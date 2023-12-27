package com.example.rickandmorty.domain.model.remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.utils.LOCATION_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = LOCATION_REMOTE_KEYS_DATABASE_TABLE)
data class LocationRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)