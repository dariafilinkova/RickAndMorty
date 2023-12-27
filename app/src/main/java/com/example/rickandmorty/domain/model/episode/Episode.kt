package com.example.rickandmorty.domain.model.episode

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmorty.utils.EPISODE_TABLE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = EPISODE_TABLE_NAME)
@TypeConverters(EpisodeCharactersConverter::class)
data class Episode(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val name: String,
    val url: String,
    val created: String
)

class EpisodeCharactersConverter {
    @TypeConverter
    fun fromEpisodeCharacters(items: List<String>): String {
        val gson = Gson()
        return gson.toJson(items)
    }

    @TypeConverter
    fun toEpisodeCharacters(itemsString: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(itemsString, type)
    }
}
