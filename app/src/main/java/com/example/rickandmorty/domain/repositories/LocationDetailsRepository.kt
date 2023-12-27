package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.model.location.ItemLocation
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocationDetailsRepository {
    fun getLocationDetails(id: Int): Flow<Resource<ItemLocation>>
}
