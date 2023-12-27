package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.dao.character.CharacterDao
import com.example.rickandmorty.data.local.dao.character.CharacterRemoteKeysDao
import com.example.rickandmorty.data.local.dao.episode.EpisodeDao
import com.example.rickandmorty.data.local.dao.episode.EpisodeRemoteKeysDao
import com.example.rickandmorty.data.local.dao.location.LocationDao
import com.example.rickandmorty.data.local.dao.location.LocationRemoteKeysDao
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.domain.model.remote_key.CharacterRemoteKeys
import com.example.rickandmorty.domain.model.remote_key.EpisodeRemoteKeys
import com.example.rickandmorty.domain.model.remote_key.LocationRemoteKeys

@Database(
    entities = [
        ItemCharacter::class, CharacterRemoteKeys::class,
        ItemLocation::class, LocationRemoteKeys::class,
        Episode::class, EpisodeRemoteKeys::class
    ],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
    abstract fun locationDao(): LocationDao
    abstract fun locationRemoteKeysDao(): LocationRemoteKeysDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun episodeRemoteKeysDao(): EpisodeRemoteKeysDao
}