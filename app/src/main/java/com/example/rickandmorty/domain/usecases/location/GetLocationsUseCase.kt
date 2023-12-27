package com.example.rickandmorty.domain.usecases.location

import com.example.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val locationRepository: LocationsRepository
) {
    operator fun invoke(
        name: String? = null,
        type: String? = null,
        dimension: String? = null
    ) = locationRepository.getAllLocations(name, type, dimension)
}