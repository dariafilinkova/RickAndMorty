package com.example.rickandmorty.domain.model.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.utils.CHARACTER_TABLE_NAME

@Entity(tableName = CHARACTER_TABLE_NAME)
data class ItemCharacter(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val gender: String,
    val status: String,
    val species: String,
    val origin: String,
)