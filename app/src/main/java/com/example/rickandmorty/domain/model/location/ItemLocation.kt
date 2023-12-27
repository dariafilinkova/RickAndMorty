package com.example.rickandmorty.domain.model.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmorty.utils.LOCATION_TABLE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = LOCATION_TABLE_NAME)
@TypeConverters(ItemLocationResidentsConverter::class)
data class ItemLocation(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url : String,
    val created : String
)

class ItemLocationResidentsConverter {
    @TypeConverter
    fun fromItemLocationResidents(residents: List<String>): String {
        val gson = Gson()
        return gson.toJson(residents)
    }

    @TypeConverter
    fun toItemLocationResidents(residentsString: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(residentsString, type)
    }
}